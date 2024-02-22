package com.todaktodak.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.todaktodak.R
import com.todaktodak.databinding.ActivityFeedbackBinding
import com.todaktodak.model.Feedback
import com.todaktodak.retrofit.RetrofitBuilder
import com.todaktodak.retrofit.RetrofitBuilder2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackActivity : AppCompatActivity() {
    lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent: Intent = getIntent()

        var result = intent.getStringExtra("data")
        var result2 = intent.getStringExtra("data2")
        var result3: Int = Integer.parseInt(result2)
        var result4 : Long = result3.toLong()
        Log.d("text13", result2.toString())

        val feedback = Feedback()
        feedback.prompt = result
        lifecycleScope.launch { Dispatchers.IO
            Feed(feedback.prompt.toString())
            delay(10000)
            feedback.aiRecommendation = binding.textView.text.toString()
            feedback.diarySeq = result4
            backFeed(feedback)
        }

        Log.d("FEED",feedback.aiRecommendation.toString())
        Log.d("FEED_Prompt",feedback.prompt.toString())
    }

    suspend fun Feed(prompt: String){
        val call = RetrofitBuilder.api.updateFeedResponse(prompt)

        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful()){ // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", "성공!"+response.body().toString())
                    binding.textView.text=response.body().toString()
                    

                }else{
                    // 통신 성공 but 응답 실패

                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    suspend fun backFeed(feedback: Feedback){
        val call = RetrofitBuilder2.api.getFeedResponse(feedback)

        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful()){ // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", "피드백 인서트 성공!"+response.body().toString())

                }else{
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
}