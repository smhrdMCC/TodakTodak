package com.todaktodak.view


import android.annotation.SuppressLint
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
import com.todaktodak.retrofit.RetrofitBuilder
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_kakao : AppCompatActivity() {
    lateinit var binding: ActivityKakakoMainBinding
    private lateinit var kakaoOauthViewModel: KakaoOauthViewModel


    //lateinit var userInfo: User2
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
        var tvNickname = findViewById<TextView>(R.id.nickname)
        val tvId = findViewById<TextView>(R.id.id)

        Log.d("NAME", findViewById<TextView>(R.id.nickname).text.toString())


        btnKakaoLogin.setOnClickListener {
            kakaoOauthViewModel.kakaoLogin()
            getInfo()

            var user = User()

            user.id = binding.id.text.toString()
            user.nickname = binding.nickname.text.toString()
            Log.d("check", user.id.toString())
            Log.d("check1", user.nickname.toString())

            Login(user)

        }

        btnKakaoLogout.setOnClickListener {
            kakaoOauthViewModel.kakaoLogout()
        }

        kakaoOauthViewModel.isLoggedIn.asLiveData().observe(this) { isLoggedIn ->
            val loginStatusInfoTitle = if (isLoggedIn) "로그인 상태" else "로그아웃 상태"
            tvLoginStatus.text = loginStatusInfoTitle

        }


    }


//    fun getInfo(): User2? {
    fun getInfo() {
        // 사용자 정보 요청 (기본)

        //var tvNickname = findViewById<TextView>(R.id.tvNickname)
        var userInfo: User? = User()
        var email : String? = "test"
        var nick : String
        var test2 : String = ""

        UserApiClient.instance.me { user, error ->

            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)

            } else if (user != null) {
            usersingleton.userId= user.id!!
            usersingleton.userNick = user.kakaoAccount?.profile?.nickname!!
                Log.d("싱글톤에 저장한 유저 아이디", usersingleton.userId.toString())
                Log.d(
                    "abcdef", "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}"

                )
                Log.d("user12", user.toString())
                binding.id.text = "${user.id}"
                binding.nickname.text = "${user.kakaoAccount?.profile?.nickname}"


                Log.d("user", binding.id.text.toString())
                Log.d("user_nick", binding.nickname.text.toString())

            }

        }

    }

    fun Login(user: User) {
        val call = RetrofitBuilder.api.getLoginResponse(user) // API의 통로 가져오고
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

