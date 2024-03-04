package com.todaktodak.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.todaktodak.databinding.ActivityFeedbackBinding
import com.todaktodak.retrofit.FeedBackSingleton
import com.todaktodak.retrofit.RetrofitBuilder2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class FeedbackActivity : AppCompatActivity() , TextToSpeech.OnInitListener{
    lateinit var binding: ActivityFeedbackBinding
    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = intent

        val diarySeq = intent.getStringExtra("diaryId")

        getFeedbackMessage(diarySeq.toString())

        binding.ttsBtn.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
            activityResult.launch(intent)
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
    private fun getFeedbackMessage(diarySeq: String){
        val call = RetrofitBuilder2.api.getFeedBackMessage(diarySeq)
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful){
                    Log.d("GetRESPONSE: ", "성공!"+response.body().toString())
                    FeedBackSingleton.FeedbackText = response.body().toString()
                    binding.textView.text = response.body()

                }else{
                    Log.d("RESPONSE", "FAILURE")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    private var activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            tts = TextToSpeech(this, this)
            tts.setPitch(0.0f)
            tts.setSpeechRate(0.9f)

        } else {
            val installIntent: Intent = Intent()
            installIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
            startActivities(installIntent)
        }
    }
    private fun startActivities(installIntent: Intent) {
        startActivity(installIntent)
    }
    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val languageStatus: Int = tts.setLanguage(Locale.KOREAN)

            if (languageStatus == TextToSpeech.LANG_MISSING_DATA ||
                languageStatus == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Toast.makeText(this, "언어 지원 불가", Toast.LENGTH_LONG).show()
            } else {

                val data: String = binding.textView.text.toString()

                var speechStatus: Int = 0

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH, null, null
                    )
                } else {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH, null, null
                    )
                }
                if (speechStatus == TextToSpeech.ERROR) {
                    Toast.makeText(this, "음성 지원 불가", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "음성 전환 오류", Toast.LENGTH_LONG).show()
        }
    }
    private fun setPitch(pitch: Float) {
        tts.setPitch(pitch)
    }
}