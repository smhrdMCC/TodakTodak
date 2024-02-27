package com.todaktodak.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.todaktodak.databinding.ActivityFeedbackBinding
import com.todaktodak.model.Feedback
import com.todaktodak.model.feedbackVO
import com.todaktodak.retrofit.RetrofitBuilder
import com.todaktodak.retrofit.RetrofitBuilder2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class FeedbackActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var binding: ActivityFeedbackBinding
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = intent

        val diaryText = intent.getStringExtra("diaryText")
        val diarySeq = intent.getStringExtra("diaryId")
        val createdAt = intent.getStringExtra("diaryDate")

        getFeedbackMessage(diarySeq.toString())
        requestChatGptFeedBack(
            prompt = feedback.prompt.toString(),
            onResult = {
                feedback.aiRecommendation = binding.textView.text.toString()
                saveChatGptFeedBack(feedback.aiRecommendation.toString() + ":" + diaryId.toString())
            }
        )

        // speech_btn 변수 초기화
//        val TTS_Btn: Button = binding.ttsBtn

        // 음성 전환 버튼 이벤트
        binding.ttsBtn.setOnClickListener {
            // intent를 통해 TTS 데이터가 설치되어 있는지 확인
            val intent: Intent = Intent()
            intent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
            activityResult.launch(intent)
        }


        // 하단 버튼
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
    fun getFeedbackMessage(diarySeq: String){
        val call = RetrofitBuilder2.api.getFeedBackMessage(diarySeq)
        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful()){ // 응답 잘 받은 경우
                    Log.d("GetRESPONSE: ", "성공!"+response.body().toString())

                    binding.textView.text = response.body()

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





    private var activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            // 음성 전환
            // TTS 데이터가 설치되어 있다면 TextToSpeech 객체 초기화
            tts = TextToSpeech(this, this)

            // pitch 조절 함수 호출 (예: 1.0은 기본값)
            tts.setPitch(0.0f)
            tts.setSpeechRate(0.9f)

        } else { // TTS 데이터가 없다면 다운로드
            // 데이터 다운로드를 위한 intent 시작
            val installIntent: Intent = Intent()
            installIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
            startActivities(installIntent)
        }
    }

    private fun startActivities(installIntent: Intent) {
        // 데이터 다운로드 액티비티 시작
        startActivity(installIntent)
    }

    // TextToSpeech 엔진 초기화시 호출되는 함수 시작 ==================================================
    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // 언어값 설정 (예: 한국어)
            val languageStatus: Int = tts.setLanguage(Locale.KOREAN)

            // TTS 엔진에서 지원하지 않는 언어나 데이터 문제 확인
            if (languageStatus == TextToSpeech.LANG_MISSING_DATA ||
                languageStatus == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Toast.makeText(this, "언어 지원 불가", Toast.LENGTH_LONG).show()
            } else { // 언어 설정 및 데이터가 정상적으로 로드됨
                // 입력값 변수에 담기
                // 아래 2줄 추가
                var feedback = Feedback()
                val data: String = feedback.prompt.toString()

                var speechStatus: Int = 0

                // 안드로이드 SDK버전에 따른 조건 (롤리팝 이상만 가능)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH, null, null
                    )
                } else {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH, null, null
                    )
                }

                // 오류가 발생한 경우 메시지 출력
                if (speechStatus == TextToSpeech.ERROR) {
                    Toast.makeText(this, "음성 지원 불가", Toast.LENGTH_LONG).show()
                }
            }
        } else {// 초기화 실패
            Toast.makeText(this, "음성 전환 오류", Toast.LENGTH_LONG).show()
        }
    } // TextToSpeech 엔진 초기화시 호출되는 함수 종료=================================================

    // pitch 값을 조절하는 함수
    private fun setPitch(pitch: Float) {
        // pitch 값을 설정
        tts.setPitch(pitch)
    }
}