package com.todaktodak.view


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
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
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKakakoMainBinding
    private lateinit var kakaoOauthViewModel: KakaoOauthViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakakoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kakaoOauthViewModel = ViewModelProvider(
            this,
            KakaoOauthViewModelFactory(application)
        )[KakaoOauthViewModel::class.java]

        val btnKakaoLogin = findViewById<ImageButton>(R.id.btnlogin)

        btnKakaoLogin.setOnClickListener {
            kakaoOauthViewModel.kakaoLogin()
            requestKaKaoUserInfo(
                onResult = {
                    val user = User()

                    user.userEmail = usersingleton.userEmail
                    user.userNick = usersingleton.userNick

                    login(
                        user,
                        onResult = {
                            Log.d("LOGIN", "SUCCESS")

                        }
                    )
                }
            )
        }
        kakaoOauthViewModel.isLoggedIn.asLiveData().observe(this) { isLoggedIn ->
            val loginStatusInfoTitle = if (isLoggedIn) "로그인 상태" else "로그아웃 상태"
            if (loginStatusInfoTitle == "로그인 상태") {
                val intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)
            }
        }

    }
    private fun requestKaKaoUserInfo(onResult: () -> Unit) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)

            } else if (user != null) {
                usersingleton.userEmail = user.id.toString()
                usersingleton.userNick = user.kakaoAccount?.profile?.nickname!!
                onResult.invoke()
            }
        }
    }
    private fun login(user: User, onResult: () -> Unit) {
        val call = RetrofitBuilder2.api.getLoginResponse(user)
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) { // 응답 잘 받은 경우
                    Log.d("LoginRESPONSE: ", response.body().toString())
                    onResult.invoke()
                } else {
                    Log.d("RESPONSE", "FAILURE")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}