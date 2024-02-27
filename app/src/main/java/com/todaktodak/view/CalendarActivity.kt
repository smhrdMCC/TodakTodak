package com.todaktodak.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.Interface.OnItemListener
import com.todaktodak.adapter.CalendarAdapter
import com.todaktodak.databinding.ActivityCalendarBinding
import com.todaktodak.model.datemailVO
import com.todaktodak.model.emotiondate
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.emotionsingleton
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity(), OnItemListener {
    lateinit var binding: ActivityCalendarBinding
    lateinit var selectedDate: LocalDate
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        selectedDate = LocalDate.now()

        loadEmotion(selectedDate)

        binding.calendarPreBtn.setOnClickListener{
            selectedDate = selectedDate.minusMonths(1)
            loadEmotion(selectedDate)
        }

        binding.calendarNextBtn.setOnClickListener{
            selectedDate = selectedDate.plusMonths(1)
            loadEmotion(selectedDate)
        }

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
    private fun setMonthView(body: ArrayList<emotiondate>?) {
        binding.calendarMonthYearText.text = monthYearFromDate(selectedDate)

        val dayList = dayInMonthArray(selectedDate)

        val emotionList = body

        val adapter = CalendarAdapter(dayList, emotionList, this)

        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        binding.calenderView.layoutManager = manager

        binding.calenderView.adapter = adapter
    }

    private fun monthYearFromDate(date: LocalDate):String{

        var formatter = DateTimeFormatter.ofPattern("MM월 yyyy년")

        return date.format(formatter)
    }
    private fun yearMonthFromDate(date: LocalDate):String{
        var formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")

        return date.format(formatter)
    }
    private fun searchingFromDate(date: LocalDate, dayText: String):String{
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM")
        var result = date.format(formatter) + "-" + dayText

        return result
    }

    private fun searchingFromMonth(date: LocalDate):String{
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM")

        return date.format(formatter)
    }
    private fun dayInMonthArray(date: LocalDate):ArrayList<String>{
        var dayList = ArrayList<String>()

        var yearMonth = YearMonth.from(date)

        var lastDay = yearMonth.lengthOfMonth()

        var firstDay = selectedDate.withDayOfMonth(1)

        var dayOfWeek = firstDay.dayOfWeek.value
        for(i in 1..41){
            if(i <= dayOfWeek || i > (lastDay + dayOfWeek)){
                dayList.add("")
            }else{
                dayList.add((i-dayOfWeek).toString())
            }
        }
        return dayList
    }
    private fun loadEmotion(selectedDate: LocalDate) {
        val call = RetrofitBuilder2.api.getEmotion(datemailVO(searchingFromMonth(selectedDate).toString(), usersingleton.userEmail))
        call.enqueue(object : Callback<ArrayList<emotiondate>> {

            override fun onResponse(
                call: Call<ArrayList<emotiondate>>,
                response: Response<ArrayList<emotiondate>>
            ) {
                if(response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                    setMonthView(response.body())

                    emotionsingleton.list = response.body()!!
                    Log.d("size check",emotionsingleton.list.size.toString())
                } else{
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }
            override fun onFailure(call: Call<ArrayList<emotiondate>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    override fun onItemClick(dayText: String, check : Boolean){
        var searching = searchingFromDate(selectedDate, dayText)
        Log.d("날짜 확인", searching)
        // 클릭한 날짜의 토스트 메세지 띄우기
        if (!check) {
            var intent = Intent(this, WriteDiaryActivity::class.java)
            intent.putExtra("date1", searching)
            startActivity(intent)
        }else if(check){
            var intent = Intent(this, GetDiaryActivity::class.java)
            intent.putExtra("date1", searching)
            startActivity(intent)
        }
    }
}