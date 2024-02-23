package com.todaktodak.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todaktodak.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE)

        var check = sharedPreferences.getString("userId", "none")

        if(check == "none"){
            // 통합 후 카카오 로그인으로 경로 지정하기 ########################################################
            var goKakao = Intent(this, DiaryListActivity::class.java)
            startActivity(goKakao)
        }else{
            // 통합 후 유저 싱글톤에 아이디 넣기 #############################################################
            var goCalendar = Intent(this, CalendarActivity::class.java)
            startActivity(goCalendar)
        }

    }
}