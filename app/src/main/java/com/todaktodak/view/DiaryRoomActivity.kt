package com.todaktodak.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todaktodak.R
import com.todaktodak.databinding.ActivityDiaryRoomBinding

class DiaryRoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityDiaryRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDiaryRoomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}