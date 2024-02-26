package com.todaktodak.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todaktodak.R
import com.todaktodak.databinding.ActivityRandomDiaryListBinding

class RandomDiaryListActivity : AppCompatActivity() {
    lateinit var binding: ActivityRandomDiaryListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRandomDiaryListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 상단 버튼
        binding.replyDiaryListBtn.setOnClickListener {
            var intent = Intent(this, ReplyDiaryListActivity::class.java)
            startActivity(intent)
        }
        binding.randomDiaryListBtn.setOnClickListener {
            var intent = Intent(this, RandomDiaryListActivity::class.java)
            startActivity(intent)
        }



        // 하단 버튼
        binding.goCalBtn.setOnClickListener {
            var intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
        binding.goListBtn.setOnClickListener {
            var intent = Intent(this, DiaryListActivity::class.java)
            startActivity(intent)
        }
        binding.goSocial.setOnClickListener {
            var intent = Intent(this, ReplyDiaryListActivity::class.java)
            startActivity(intent)
        }
        binding.goMypage.setOnClickListener {
            var intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }
}