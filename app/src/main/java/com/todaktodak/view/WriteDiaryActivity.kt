package com.todaktodak.view

import android.content.Intent
import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mccproject.Model.Diary
import com.github.mikephil.charting.animation.Easing
import com.google.android.material.animation.MatrixEvaluator
import com.todaktodak.databinding.ActivityWriteDiaryBinding
import com.todaktodak.model.Feedback
import com.todaktodak.retrofit.DiarySeqSingleton
import com.todaktodak.retrofit.DiaryTextSingleTon
import com.todaktodak.retrofit.RetrofitBuilder
import com.todaktodak.retrofit.RetrofitBuilder
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.RetrofitBuilderBert
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteDiaryActivity : AppCompatActivity() {

    lateinit var binding: ActivityWriteDiaryBinding
    var user_email: String = usersingleton.userEmail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.sendBtn.onTouchEvent(
//            animateY(1400, Easing.EaseInOutQuad)
//                    animate()
//        )

        // plainText에 일기 쓰기
        binding.sendBtn.setOnClickListener {
            var diary_content = binding.writeDiary.text.toString()
            DiaryTextSingleTon.diaryText = binding.writeDiary.text.toString()

            var writtenDiary = Diary()
            writtenDiary.diaryContent = diary_content
            writtenDiary.userEmail = user_email
            var date1 = intent.getStringExtra("date1")
            saveDiary(writtenDiary.diaryContent.toString() + ":" +writtenDiary.userEmail.toString() +":" + date1.toString())
            sendBert(writtenDiary.diaryContent.toString())
            saveDiary(writtenDiary.diaryContent.toString() + ":" + writtenDiary.userEmail.toString() + ":" + date1.toString())

            var intent = Intent(this, GetDiaryActivity::class.java)
            intent.putExtra("diaryContent", diary_content)
            intent.putExtra("userEmail", user_email) // 위에 변수 선언 부분 참고하기

            intent.putExtra("date1", date1)
            Log.d("다이어리", DiaryTextSingleTon.diaryText)
            val feedback = Feedback()
            feedback.prompt = DiaryTextSingleTon.diaryText


            requestChatGptFeedBack(
                prompt = feedback.prompt.toString(),
                onResult = {
                    feedback.aiRecommendation = binding.textView2.text.toString()
                    saveChatGptFeedBack(feedback.aiRecommendation.toString() + ":" + DiarySeqSingleton.diarySeq)
                }
            )
            startActivity(intent)
        }

    }

    fun saveDiary(diary: String) {
        val call = RetrofitBuilder2.api.doGetDiary(diary)
        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("SaveRESPONSE: ", "저장 성공!!" + response.body().toString())
                    DiarySeqSingleton.diarySeq = response.body().toString()
                } else {
                    // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "저장 실패!!")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("Save CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    private fun requestChatGptFeedBack(prompt: String, onResult: () -> Unit) {
        val call = RetrofitBuilder.api.updateFeedResponse(prompt)

        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) { // 응답 잘 받은 경우
                    Log.d("GPT RESPONSE: ", "성공!" + response.body().toString())

                    binding.textView2.text = response.body().toString() //chatGpt prompt
                    binding.textView2.visibility = View.GONE
                    onResult.invoke()
                } else {
                    // 통신 성공 but 응답 실패
                    Log.d("GPT RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    private fun saveChatGptFeedBack(feedback: String) {
        val call = RetrofitBuilder2.api.getFeedResponse(feedback)

        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", "피드백 인서트 성공!" + response.body().toString())

                } else {
                    // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "피드백 인서트 FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    fun sendBert(diary: String) {
        val call = RetrofitBuilderBert.api.sendBert(diary)
        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", "저장 성공!!" + response.body().toString())
                    Log.d("확인",response.body().toString())
                } else {
                    // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "저장 실패!!")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

}


