package com.todaktodak.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.todaktodak.databinding.ActivityDiaryBinding
import com.todaktodak.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiaryActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiaryBinding
    var user_email = "test4"
    var diary_content = ""
    var created_at = "2024-02-21"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        diary_content = intent.getStringExtra("diaryContent").toString()
        user_email = intent.getStringExtra("email").toString()

        val intent = intent

        val diaryContent = intent.getStringExtra("diaryContent")
        val userEmail = intent.getStringExtra("userEmail")

        if (diaryContent != null && userEmail != null) {
            // diaryContent와 userEmail을 사용하여 원하는 작업 수행
            // 예: TextView에 텍스트 설정
            binding.showDiary.text = diaryContent
//            binding.userEmail.text = userEmail

            // findDiary 함수 호출
           //findDiary("$diaryContent")  // 두 매개 변수를 합쳐서 전달
            Log.d("success","조회성공")
        }

//+        intent = findDiary().toString()

        // WriteDiary로 돌아가는 버튼 이벤트
        //================================================
        binding.imgBtn.setOnClickListener {
            setResult(RESULT_OK, intent)
            finish()
        }
        //================================================


//        diary_content = intent.getStringExtra("data")
//        val tb_diary = FindDiary(user_email, diary_content, created_at)
//        Log.e("test", tb_diary.toString())
//        findDiary(tb_diary)
//        Log.d("findDiary", "값 가져오기")
//        var result = Intent(this, ShowDiaryActivity::class.java)

//        Log.d("result", "조회가 성공인가?")
//        binding.diary.setText(findDiary(tb_diary).toString())
//         WriteDiary의 myDiary를 띄워주는 곳
//        intent.getStringExtra("data")
    }

//    fun findDiary(findDiary: String) {
////      var findDiary = FindDiary(user_email, diary_content, created_at)
//        val call = RetrofitBuilder.api.findDiary(findDiary)
//        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
//            override fun onResponse( // 통신에 성공한 경우
//                call: Call<String>,
//                response: Response<String>
//            ) {
//                if (response.isSuccessful()) { // 응답 잘 받은 경우
//                    Log.d("RESPONSE: ", "조회 성공!" + response.body().toString())
//                    binding.showDiary.text = response.body().toString()
//                    Log.d("success", response.body().toString())
//                } else {
//                    // 통신 성공 but 응답 실패
//                    Log.d("RESPONSE", "조회 실패!!")
//                }
//            }
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                // 통신에 실패한 경우
//                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
//            }
//        })
//    }
}




































