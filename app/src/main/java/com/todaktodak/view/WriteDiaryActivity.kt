package com.todaktodak.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mccproject.Model.Diary
import com.todaktodak.databinding.ActivityWriteDiaryBinding

class WriteDiaryActivity : AppCompatActivity() {

    lateinit var binding: ActivityWriteDiaryBinding
    var user_email: String = "test4"
    var diary_content: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // plainText에 일기 쓰기
        // 일기작성 버튼 클릭했을 때 결과가지고 작성된 일기 화면으로 화면 전환이벤트
        binding.sendBtn.setOnClickListener {
            diary_content = binding.writeDiary.text.toString()
            
            var writtenDiary = Diary()
            writtenDiary.diaryContent = diary_content
            writtenDiary.userEmail = user_email

            //saveDiary(writtenDiary.diaryContent.toString() + ":" +writtenDiary.userEmail.toString())

            var intent = Intent(this, DiaryActivity::class.java)
            intent.putExtra("diaryContent", diary_content)
            intent.putExtra("userEmail", user_email) // 위에 변수 선언 부분 참고하기
            mainLauncher.launch(intent)
        }
    }

    val mainLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(applicationContext, "성공!", Toast.LENGTH_SHORT).show()
        }
    }

//    fun saveDiary(diary: String) {
//        val call = RetrofitBuilder.api.doGetDiary(diary)
//        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
//            override fun onResponse( // 통신에 성공한 경우
//                call: Call<String>,
//                response: Response<String>
//            ) {
//                if (response.isSuccessful()) { // 응답 잘 받은 경우
//                    Log.d("RESPONSE: ", "저장 성공!!" + response.body().toString())
//
//                } else {
//                    // 통신 성공 but 응답 실패
//                    Log.d("RESPONSE", "저장 실패!!")
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                // 통신에 실패한 경우
//                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
//            }
//        })
//    }



}























