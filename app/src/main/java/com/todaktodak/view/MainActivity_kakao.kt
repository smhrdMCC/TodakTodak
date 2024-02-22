package com.todaktodak.view


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.kakao.sdk.user.Constants.TAG
import com.kakao.sdk.user.UserApiClient
import com.todaktodak.R
import com.todaktodak.databinding.ActivityKakakoMainBinding
import com.todaktodak.kakao.KakaoOauthViewModel
import com.todaktodak.kakao.KakaoOauthViewModelFactory
import com.todaktodak.model.User
import com.todaktodak.retrofit.RetrofitBuilder2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_kakao : AppCompatActivity() {
    lateinit var binding: ActivityKakakoMainBinding
    private lateinit var kakaoOauthViewModel: KakaoOauthViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakakoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewModelProvider를 통해 ViewModel 인스턴스 생성
        kakaoOauthViewModel = ViewModelProvider(
            this,
            KakaoOauthViewModelFactory(application)
        ).get(KakaoOauthViewModel::class.java)

        val btnKakaoLogin = findViewById<ImageButton>(R.id.btnlogin)
        val btnKakaoLogout = findViewById<Button>(R.id.btn_kakao_logout)
        val tvLoginStatus = findViewById<TextView>(R.id.tv_login_status)

        btnKakaoLogin.setOnClickListener {
            kakaoOauthViewModel.kakaoLogin()
            getInfo()

            var user = User()

            user.userEmail = binding.id.text.toString()
            user.userNick = binding.nickname.text.toString()
            Login(user)

        }

        btnKakaoLogout.setOnClickListener {
            kakaoOauthViewModel.kakaoLogout()
        }

        kakaoOauthViewModel.isLoggedIn.asLiveData().observe(this) { isLoggedIn ->
            val loginStatusInfoTitle = if (isLoggedIn) "로그인 상태" else "로그아웃 상태"
            tvLoginStatus.text = loginStatusInfoTitle

        }

        binding.btnnext.setOnClickListener {
            val intent = Intent(this, DiaryListActivity::class.java)
            startActivity(intent)
        }
    }

    fun getInfo() {
        UserApiClient.instance.me { user, error ->

            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)

            } else if (user != null) {

                Log.d(
                    "abcdef", "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}"
                )
                binding.id.text = "${user.id}"
                binding.nickname.text = "${user.kakaoAccount?.profile?.nickname}"
            }
        }
    }

    fun Login(user: User) {
        val call = RetrofitBuilder2.api.getLoginResponse(user) // API의 통로 가져오고
        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", response.body().toString())

                } else {
                    // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}