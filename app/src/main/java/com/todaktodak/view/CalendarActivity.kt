package com.todaktodak.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.Interface.OnItemListener
import com.todaktodak.adapter.CalendarAdapter
import com.todaktodak.databinding.ActivityCalendarBinding
import com.todaktodak.model.datemailVO
import com.todaktodak.model.emotiondate
import com.todaktodak.retrofit.RetrofitBuilder2
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

        // 현재 날짜
        selectedDate = LocalDate.now()

        // 화면 설정
        loadEmotion(selectedDate)

        // 이전달 버튼 이벤트
        binding.calendarPreBtn.setOnClickListener{
            // 현재 월 -1 변수에 담기
            selectedDate = selectedDate.minusMonths(1)
            loadEmotion(selectedDate)
        }

        // 다음달 버튼 이벤트
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
        binding.goSocial.setOnClickListener {

        }
        binding.goMypage.setOnClickListener {
            var intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }

    // 날짜와 감정이모티콘 화면에 보여주기
    private fun setMonthView(body: ArrayList<emotiondate>?) {
        // 년월 텍스트뷰 세팅
        binding.calendarMonthYearText.text = monthYearFromDate(selectedDate)

        // 날짜 생성해서 리스트에 담기
        val dayList = dayInMonthArray(selectedDate)

        // 날짜별 감정을 리스트에 담기
        val emotionList = body

        // 어댑터 초기화
        val adapter = CalendarAdapter(dayList, emotionList, this)

        // 레이아웃 설정(열 7개)
        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        // 레이아웃 적용
        binding.calenderView.layoutManager = manager

        // 어댑터 적용
        binding.calenderView.adapter = adapter
    }

    // 날짜 타입 설정(월,년)
    private fun monthYearFromDate(date: LocalDate):String{

        var formatter = DateTimeFormatter.ofPattern("MM월 yyyy년")

        // 받아온 날짜를 해당 포맷으로 변경
        return date.format(formatter)
    }

    // 날짜 타입 설정(년,월)
    private fun yearMonthFromDate(date: LocalDate):String{

        var formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")

        // 받아온 날짜를 해당 포맷으로 변경
        return date.format(formatter)
    }

    // 검색용 날짜 타입 생성(yyyy-mm-dd)
    private fun searchingFromDate(date: LocalDate, dayText: String):String{
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM")
        var result = date.format(formatter) + "-" + dayText

        return result
    }

    // 검색용 날짜 타입 생성(yyyy-mm)
    private fun searchingFromMonth(date: LocalDate):String{
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM")

        return date.format(formatter)
    }

    // 해당 달의 일 리스트 생성
    private fun dayInMonthArray(date: LocalDate):ArrayList<String>{
        var dayList = ArrayList<String>()

        var yearMonth = YearMonth.from(date)

        // 해당 월 마지막 날짜 가져오기(예: 28,30,31)
        var lastDay = yearMonth.lengthOfMonth()

        // 해당 월의 첫 번째 날 가져오기(예: 4월 1일)
        var firstDay = selectedDate.withDayOfMonth(1)

        // 첫 번째 날 요일 맞추기(월:1, 일:7)
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

    // 감정 HTTP 요청과 응답
    private fun loadEmotion(selectedDate: LocalDate) {
        val call = RetrofitBuilder2.api.getEmotion(datemailVO(searchingFromMonth(selectedDate).toString(), usersingleton.userEmail))
        call.enqueue(object : Callback<ArrayList<emotiondate>> {

            // 통신에 성공한 경우
            override fun onResponse(
                call: Call<ArrayList<emotiondate>>,
                response: Response<ArrayList<emotiondate>>
            ) {
                if(response.isSuccessful()){ // 응답을 잘 받은 경우
                    Log.d("RESPONSE: ", response.body().toString())
                    setMonthView(response.body())
                } else { // 통신은 성공했지만 응답에 문제가 있는 경우
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            // 통신에 실패한 경우
            override fun onFailure(call: Call<ArrayList<emotiondate>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    // 아이템 클릭 이벤트
    override fun onItemClick(dayText: String, check : Boolean){
        // 선택한 날짜를 yyyy-MM-DD 형태로 변경
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