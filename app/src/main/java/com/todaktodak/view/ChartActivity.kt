package com.todaktodak.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.todaktodak.R
import com.todaktodak.databinding.ActivityChartBinding
import com.todaktodak.retrofit.emotionsingleton

class ChartActivity : AppCompatActivity() {
    lateinit var binding: ActivityChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNutrientPieChart()
        // 하단 버튼
        binding.goCalBtn.setOnClickListener {
            var intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
        binding.goListBtn.setOnClickListener {
            var intent = Intent(this, DiaryListActivity::class.java)
            startActivity(intent)
        }
        binding.goChart.setOnClickListener {
            var intent = Intent(this, ChartActivity::class.java)
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

    private fun initNutrientPieChart() {

        binding.piechartFeedNutrient.setUsePercentValues(true)
        var emotionList = emotionsingleton.list.size
        val numbers: Array<Int> = Array<Int>(8) { 0 }

        for (i in 0..emotionList - 1) {
            if (emotionsingleton.list.get(i).emotion.toString() == "불안") {
                numbers[0] = numbers[0] + 10
            } else if (emotionsingleton.list.get(i).emotion.toString() == "당황") {
                numbers[1] = numbers[1] + 10
            } else if (emotionsingleton.list.get(i).emotion.toString() == "분노") {
                numbers[2] = numbers[2] + 10
            } else if (emotionsingleton.list.get(i).emotion.toString() == "슬픔") {
                numbers[3] = numbers[3] + 10
            } else if (emotionsingleton.list.get(i).emotion.toString() == "중립") {
                numbers[4] = numbers[4] + 10
            } else if (emotionsingleton.list.get(i).emotion.toString() == "행복") {
                numbers[5] = numbers[5] + 10
            } else if (emotionsingleton.list.get(i).emotion.toString() == "혐오") {
                numbers[6] = numbers[6] + 10
            } else if (emotionsingleton.list.get(i).emotion.toString() == "기쁨") {
                numbers[7] = numbers[7] + 10
            }
        }
        val emotionText = listOf("불안","당황","분노","슬픔","중립","행복","혐오","기쁨")
        val entries = ArrayList<PieEntry>()
        for(i in 0 .. numbers.size -1) {
            if (numbers[i] != 0) {
                entries.add(PieEntry(numbers[i].toFloat(), emotionText[i]))
            }
        }
        val pieColors = listOf(

            ContextCompat.getColor(this, R.color.pastel_color1),
            ContextCompat.getColor(this, R.color.pastel_color2),
            ContextCompat.getColor(this, R.color.pastel_color3),
            ContextCompat.getColor(this, R.color.pastel_color4),
            ContextCompat.getColor(this, R.color.pastel_color5),
            ContextCompat.getColor(this, R.color.pastel_color6),
            ContextCompat.getColor(this, R.color.pastel_color7),
            ContextCompat.getColor(this, R.color.pastel_color8),

        )
        val dataSet = PieDataSet(entries, "")

        dataSet.colors = pieColors
        dataSet.sliceSpace

        dataSet.setDrawValues(false)

        binding.piechartFeedNutrient.apply {
            data = PieData(dataSet)


            description.isEnabled = false
            legend.isEnabled = true
            isRotationEnabled = true
            holeRadius = 60f
            centerText = "감정 표현"
            setTouchEnabled(false)
            setEntryLabelColor(Color.BLACK)
            animateY(1200, Easing.EaseInOutCubic)

            animate()
        }
    }
}