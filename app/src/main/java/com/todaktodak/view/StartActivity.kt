package com.todaktodak.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todaktodak.R
import com.todaktodak.retrofit.usersingleton

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE)

        var check = sharedPreferences.getString("userId", "none")

        if(check == "none"){
            var goKakao = Intent(this, MainActivity_kakao::class.java)
            startActivity(goKakao)
        }else{
            usersingleton.userEmail = check!!
            var goCalendar = Intent(this, CalendarActivity::class.java)
            startActivity(goCalendar)
        }
    }
}