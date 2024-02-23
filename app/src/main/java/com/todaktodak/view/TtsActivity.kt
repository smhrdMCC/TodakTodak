package com.todaktodak.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.todaktodak.databinding.ActivityTtsBinding
import java.util.Locale

class TtsActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var diary: EditText
    lateinit var tts: TextToSpeech
    lateinit var binding: ActivityTtsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTtsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 입력창
        diary = binding.showDiary

        // speech_btn 변수 초기화
        val TTS_Btn: Button = binding.ttsBtn

        // 음성 전환 버튼 이벤트
        TTS_Btn.setOnClickListener {
            // intent를 통해 TTS 데이터가 설치되어 있는지 확인
            val intent: Intent = Intent()
            intent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
            activityResult.launch(intent)
        }
    }//onCreate

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
                val data: String = diary.text.toString()

                var speechStatus: Int = 0

                // 안드로이드 SDK버전에 따른 조건 (롤리팝 이상만 가능)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH,null, null)
                } else {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH,null, null)
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