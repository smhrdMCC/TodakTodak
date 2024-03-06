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
//import com.todaktodak.retrofit.RetrofitBuilderBert
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

        var info = datemailVO(date.toString(), usersingleton.userEmail)

        var date1 = intent.getStringExtra("date1")
        binding.getDiaryUserNick.setText('"'+ usersingleton.userNick + '"'+" 님의"+date1+"일 일기")

        getSendMessage(info)
        binding.feedBtn.setOnClickListener {
            var goFeedbackButton = Intent(this, FeedbackActivity::class.java)
            var diaryText = binding.showDiary.text.toString()
            var diaryId = binding.getDId.text.toString()

            goFeedbackButton.putExtra("diaryText", diaryText)
            goFeedbackButton.putExtra("diaryId", diaryId)
            intent.putExtra("diaryDate",date)
            startActivity(goFeedbackButton)

            finish()
        }
        binding.alterBtn.setOnClickListener {
            var diary_content = binding.showDiary.toString()
            var diarySequence = binding.getDId.text.toString()
            var AlterDiaryActivity = Intent(this, AlterDiaryActivity::class.java)

            AlterDiaryActivity.putExtra("diaryContent", diary_content)
            AlterDiaryActivity.putExtra("user_email",usersingleton.userEmail)
            AlterDiaryActivity.putExtra("diarySequence", diarySequence)
            startActivity(AlterDiaryActivity)
            finish()
        }
    }
    private fun getSendMessage(info: datemailVO) {
        val call = RetrofitBuilder2.api.getMsgResponse(info)
        call.enqueue(object : Callback<List<seqcont>> {
            override fun onResponse(
                call: Call<List<seqcont>>,
                response: Response<List<seqcont>>
            ) {
                if (response.isSuccessful) {
                    val text1 = response.body()
                    binding.showDiary.text = text1?.get(0)?.content
                    binding.getDId.text = text1?.get(0)?.diarySeq.toString()
                    binding.getDId.visibility = View.GONE
                } else {
                    Log.d("RESPONSE", "FAILURE")
                }
            }
            override fun onFailure(call: Call<List<seqcont>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}