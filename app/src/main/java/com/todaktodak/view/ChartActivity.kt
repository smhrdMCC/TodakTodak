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
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.todaktodak.R
import com.todaktodak.databinding.ActivityChartBinding
import com.todaktodak.retrofit.emotionsingleton

class ChartActivity : AppCompatActivity() {
    lateinit var binding: ActivityChartBinding
    val numbers: Array<Int> = Array<Int>(8) { 0 }
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


        var emotionList = emotionsingleton.list.size


        for (i in 0..emotionList - 1) {
            if (emotionsingleton.list.get(i).emotion.toString() == "불안") {
                numbers[0] = numbers[0] + 1
            } else if (emotionsingleton.list.get(i).emotion.toString() == "당황") {
                numbers[1] = numbers[1] + 1
            } else if (emotionsingleton.list.get(i).emotion.toString() == "분노") {
                numbers[2] = numbers[2] + 1
            } else if (emotionsingleton.list.get(i).emotion.toString() == "슬픔") {
                numbers[3] = numbers[3] + 1
            } else if (emotionsingleton.list.get(i).emotion.toString() == "중립") {
                numbers[4] = numbers[4] + 1
            } else if (emotionsingleton.list.get(i).emotion.toString() == "행복") {
                numbers[5] = numbers[5] + 1
            } else if (emotionsingleton.list.get(i).emotion.toString() == "혐오") {
                numbers[6] = numbers[6] + 1
            } else if (emotionsingleton.list.get(i).emotion.toString() == "기쁨") {
                numbers[7] = numbers[7] + 1
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
        dataSet.setValueFormatter(PercentFormatter())
        dataSet.colors = pieColors
        dataSet.sliceSpace = 5f

        dataSet.setDrawValues(false)
        binding.run {
            binding.piechartFeedNutrient.apply {
                data = PieData(dataSet)

                description.isEnabled = false
                legend.isEnabled = false
                isRotationEnabled = true
                holeRadius = 60f
                centerText = "2월의 감정"
                //setTouchEnabled(false)
                setEntryLabelColor(Color.BLACK)
                animateY(1200, Easing.EaseInOutCubic)

                animate()
            }
            val gson = Gson()

            // 원본 데이터를 JSON 형식으로 직렬화
            val jsonString = gson.toJson(entries)

            // JSON 형식의 데이터를 다시 역직렬화하여 리스트로 변환
            val typeToken = object : TypeToken<List<PieEntry>>() {}.type
            val copiedList = gson.fromJson<List<PieEntry>>(jsonString, typeToken)

            // 내림차순으로 정렬
            val sortedList = copiedList.sortedByDescending { it.value }

            // 정렬된 데이터 중에서 인덱스 0부터 2까지 추출
            val selectedDataList = sortedList.subList(0, 8)

            emoSort1.setText("1st: ${selectedDataList[0].label} -> ${selectedDataList[0].value}")
            emoSort2.setText("2nd: ${selectedDataList[1].label} -> ${selectedDataList[1].value}")
            emoSort3.setText("3rd: ${selectedDataList[2].label} -> ${selectedDataList[2].value}")
            emoSort4.setText("3rd: ${selectedDataList[3].label} -> ${selectedDataList[3].value}")
            emoSort5.setText("3rd: ${selectedDataList[4].label} -> ${selectedDataList[4].value}")
            emoSort6.setText("3rd: ${selectedDataList[5].label} -> ${selectedDataList[5].value}")
            emoSort7.setText("3rd: ${selectedDataList[6].label} -> ${selectedDataList[6].value}")
            emoSort8.setText("3rd: ${selectedDataList[7].label} -> ${selectedDataList[7].value}")

        }

    }
}