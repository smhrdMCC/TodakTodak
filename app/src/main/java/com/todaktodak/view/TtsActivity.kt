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

        diary = binding.showDiary

        val TTS_Btn: Button = binding.ttsBtn

        TTS_Btn.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
            activityResult.launch(intent)
        }
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

                val data: String = diary.text.toString()

                var speechStatus: Int = 0

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH,null, null)
                } else {
                    speechStatus = tts.speak(
                        data, TextToSpeech.QUEUE_FLUSH,null, null)
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