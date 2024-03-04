package com.todaktodak.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.todaktodak.databinding.ActivityRandomDiaryRoomBinding
import com.todaktodak.model.linkDiary
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomDiaryRoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRandomDiaryRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRandomDiaryRoomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mail = intent.getStringExtra("email")
        val nick = intent.getStringExtra("nick")
        val content = intent.getStringExtra("content")

        binding.randomRoomPartnerNickView.text = nick
        binding.randomDiaryRoomContent.text = content

        binding.sendDiaryBtn.setOnClickListener {
            val inputContent = binding.replyDiaryInputBox.text.toString()
            linkUserDiary(mail, usersingleton.userEmail, content, inputContent)
        }

    }

    private fun linkUserDiary(toUser: String?, fromUser: String?, content:String?, inputContent: String?) {
        val call = RetrofitBuilder2.api.linkUserDiary(linkDiary(toUser, fromUser, content, inputContent))
        call.enqueue(object : Callback<String> {

            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful){
                    goReplyList()
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    fun goReplyList(){
        var intent = Intent(this, ReplyDiaryListActivity::class.java)
        startActivity(intent)
    }
}