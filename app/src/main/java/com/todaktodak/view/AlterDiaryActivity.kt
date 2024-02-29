package com.todaktodak.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mccproject.Model.Diary
import com.todaktodak.databinding.ActivityAlterDiaryBinding
import com.todaktodak.model.Feedback
import com.todaktodak.retrofit.DiarySeqSingleton
import com.todaktodak.retrofit.DiaryTextSingleTon
import com.todaktodak.retrofit.RetrofitBuilder
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.RetrofitBuilderBert
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlterDiaryActivity : AppCompatActivity() {

    lateinit var binding: ActivityAlterDiaryBinding
    var user_email: String = usersingleton.userEmail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlterDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView4.visibility = View.GONE

        binding.sendBtn.setOnClickListener {
            var diary_content = binding.writeDiary.text.toString()
            DiaryTextSingleTon.diaryText = binding.writeDiary.text.toString()

            var writtenDiary = Diary()
            writtenDiary.diaryContent = diary_content
            writtenDiary.userEmail = user_email
            var date1 = intent.getStringExtra("date1")
            var diarySequence = intent.getStringExtra("diarySequence")
            alterDiary(diarySequence.toString() +":"+writtenDiary.diaryContent.toString() + ":" +writtenDiary.userEmail.toString() +":" + date1.toString())

            // Read diary
            sendBert(diary_content)


            var intent = Intent(this, GetDiaryActivity::class.java)
            intent.putExtra("diaryContent", diary_content)
            intent.putExtra("userEmail", user_email)

            intent.putExtra("date1", date1)
            Log.d("다이어리", DiaryTextSingleTon.diaryText)
            val feedback = Feedback()
            feedback.prompt = DiaryTextSingleTon.diaryText

            requestChatGptFeedBack(
                prompt = feedback.prompt.toString(),
                onResult = {
                    feedback.aiRecommendation = binding.textView4.text.toString()
                    saveChatGptFeedBack(feedback.aiRecommendation.toString() + ":" + DiarySeqSingleton.diarySeq)
                }
            )
            startActivity(intent)
        }

        binding.shareBtn.setOnClickListener {
            var diary_content = binding.writeDiary.text.toString()
            var writtenDiary = Diary(user_email, diary_content)
            var writtenDiaryData = writtenDiary.diaryContent.toString() + ":" +writtenDiary.userEmail.toString()

            openDiary(writtenDiaryData)

            var intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

    }
    fun alterDiary(diary: String) {
        val call = RetrofitBuilder2.api.alterDiary(diary)
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

                    binding.textView4.text = response.body().toString() //chatGpt prompt
                    binding.textView4.visibility = View.GONE
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

        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) {
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
    // Communicate with Flask server using String format
    fun sendBert(data: String) {
        val call = RetrofitBuilderBert.api.sendDataToFlask(data)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d("SendBert RESPONSE:", "Data sent & recieved successfully: " + response.body().toString())
                    // Insert code if handling response needed

                } else {
                    Log.d("SendBert RESPONSE:", "Failed to send data.")
                    // Insert code if handling error needed
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("SendBert CONNECTION FAILURE:", t.localizedMessage)
                // Handle failure if needed
            }
        })
    }

    fun openDiary(writtenDiaryData: String){
        val call = RetrofitBuilder2.api.sendOpenDiary(writtenDiaryData)
        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("SaveRESPONSE: ", "저장 성공!!" + response.body().toString())
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

}

