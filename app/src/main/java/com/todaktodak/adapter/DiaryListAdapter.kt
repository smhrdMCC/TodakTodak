package com.todaktodak.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todaktodak.R

class DiaryListAdapter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diary_list_cell)
    }
}