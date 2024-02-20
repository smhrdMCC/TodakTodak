package com.todaktodak.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todaktodak.R

class CalendarAdapter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_cell)
    }
}