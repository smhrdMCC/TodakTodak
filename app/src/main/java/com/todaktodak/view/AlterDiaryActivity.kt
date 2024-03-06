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

            sendBert(diary_content)

            var intent = Intent(this, GetDiaryActivity::class.java)
            intent.putExtra("diaryContent", diary_content)
            intent.putExtra("userEmail", user_email)

            intent.putExtra("date1", date1)
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
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) {
                    DiarySeqSingleton.diarySeq = response.body().toString()
                } else {
                    Log.d("RESPONSE", "저장 실패!!")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Save CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    private fun requestChatGptFeedBack(prompt: String, onResult: () -> Unit) {
        val call = RetrofitBuilder.api.updateFeedResponse(prompt)

        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    binding.textView4.text = response.body().toString() //chatGpt prompt
                    binding.textView4.visibility = View.GONE
                    onResult.invoke()
                } else {
                    Log.d("GPT RESPONSE", "FAILURE")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
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
                    Log.d("RESPONSE", "피드백 인서트 FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    fun sendBert(data: String) {
        val call = RetrofitBuilderBert.api.sendDataToFlask(data)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d("SendBert RESPONSE:", "Data sent & recieved successfully: " + response.body().toString())

                } else {
                    Log.d("SendBert RESPONSE:", "Failed to send data.")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("SendBert CONNECTION FAILURE:", t.localizedMessage)
            }
        })
    }

    fun openDiary(writtenDiaryData: String){
        val call = RetrofitBuilder2.api.sendOpenDiary(writtenDiaryData)
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful()) {
                    Log.d("SaveRESPONSE: ", "저장 성공!!" + response.body().toString())
                } else {
                    Log.d("RESPONSE", "저장 실패!!")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Save CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

}


