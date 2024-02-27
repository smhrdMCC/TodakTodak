package com.todaktodak.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mccproject.Model.Diary
import com.example.mccproject.Model.FindDiary
import com.todaktodak.databinding.ActivityDiaryBinding
import com.todaktodak.model.datemailVO
import com.todaktodak.model.seqcont
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class GetDiaryActivity : AppCompatActivity(){

    lateinit var binding: ActivityDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var date = intent.getStringExtra("date1")
        val getmsg = FindDiary()
        getmsg.userEmail = usersingleton.userEmail

        binding.backWriteDiary.setOnClickListener{
            val backWriteDiaryButton = Intent(this, WriteDiaryActivity::class.java)
            startActivity(backWriteDiaryButton)
            finish()
        }

        var info = datemailVO(date.toString(), usersingleton.userEmail)

        getSendMessage(info)
        binding.feedBtn.setOnClickListener {
            var goFeedbackButton = Intent(this, FeedbackActivity::class.java)
            var diaryText = binding.showDiary.text.toString()
            var diaryId = binding.getDId.text.toString()

            goFeedbackButton.putExtra("diaryText", diaryText)
            goFeedbackButton.putExtra("diaryId", diaryId)
            startActivity(goFeedbackButton)
            intent.putExtra("diaryText",diaryText)
            intent.putExtra("diaryId",diaryId)
            intent.putExtra("diaryDate",date)
            startActivity(intent)
            finish()
        }
    }

    fun getSendMessage(info: datemailVO) {
        val call = RetrofitBuilder2.api.getMsgResponse(info)
        call.enqueue(object : Callback<List<seqcont>> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<List<seqcont>>,
                response: Response<List<seqcont>>
            ) {
                if (response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("GetDiaryRESPONSE: ", "성공!" + response.body().toString())
                    val text1 = response.body()
                    binding.showDiary.text = text1?.get(0)?.content
                    binding.getDId.text = text1?.get(0)?.diarySeq.toString()
                    binding.getDId.visibility = View.GONE
                } else {
                    // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<List<seqcont>>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}