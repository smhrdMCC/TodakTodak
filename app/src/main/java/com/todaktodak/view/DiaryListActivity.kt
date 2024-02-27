package com.todaktodak.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.adapter.DiaryListAdapter
import com.todaktodak.databinding.ActivityDiaryListBinding
import com.todaktodak.model.datemailVO
import com.todaktodak.model.emotionContentVO
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class DiaryListActivity : AppCompatActivity() {
    lateinit var binding: ActivityDiaryListBinding
    lateinit var selectedDate: LocalDate
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDiaryListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        selectedDate = LocalDate.now()

        loadMonthlyDiaryList(selectedDate)

        binding.listPreBtn.setOnClickListener{
            selectedDate = selectedDate.minusMonths(1)
            loadMonthlyDiaryList(selectedDate)
        }

        binding.listNextBtn.setOnClickListener{
            selectedDate = selectedDate.plusMonths(1)
            loadMonthlyDiaryList(selectedDate)
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
    private fun loadMonthlyDiaryList(selectedDate: LocalDate) {
        val call = RetrofitBuilder2.api.getDiaryList(datemailVO(searchingFromMonth(selectedDate), usersingleton.userEmail))
        call.enqueue(object : Callback<ArrayList<emotionContentVO>> {

            override fun onResponse(
                call: Call<ArrayList<emotionContentVO>>,
                response: Response<ArrayList<emotionContentVO>>
            ) {
                if(response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                    setListView(response.body())
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            override fun onFailure(call: Call<ArrayList<emotionContentVO>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    private fun setListView(body: ArrayList<emotionContentVO>?) {
        binding.listMonthYearText.text = monthYearFromDate(selectedDate)

        val adapter = DiaryListAdapter(body, this)

        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 1)

        binding.listView.layoutManager = manager

        binding.listView.adapter = adapter
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
    fun onItemClick(dayText: String,  check : Boolean){
        var searching = searchingFromDate(selectedDate, dayText)
        Log.d("날짜 확인", searching)
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