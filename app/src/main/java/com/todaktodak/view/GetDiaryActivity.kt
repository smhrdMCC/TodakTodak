package com.todaktodak.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mccproject.Model.FindDiary
import com.todaktodak.databinding.ActivityDiaryBinding
import com.todaktodak.retrofit.RetrofitBuilder2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDiaryActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getmsg = FindDiary()

        getmsg.userEmail = user
        getSendMessage(getmsg)
        binding.feedBtn.setOnClickListener {
            var intent = Intent(this,FeedbackActivity::class.java)
            var data = binding.showDiary.text.toString()
            var data2 = binding.textView2.text
            intent.putExtra("data",data)
            intent.putExtra("data2",data2)
            startActivity(intent)
            finish()
        }

    }
    fun getSendMessage(user_email : FindDiary){
        val call = RetrofitBuilder2.api.getMsgResponse(user_email)
        call.enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful()){ // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", "성공!"+response.body().toString())
                    val text = response.body().toString()

                    binding.getMsg.text = text.split(":")[0]
                    binding.textView2.text = text.split(":")[1]

                    binding.textView2.visibility = View.GONE
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


}




































