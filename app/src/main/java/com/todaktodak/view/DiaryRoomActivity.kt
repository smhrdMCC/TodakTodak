package com.todaktodak.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.adapter.DiaryRoomAdapter
import com.todaktodak.databinding.ActivityDiaryRoomBinding
import com.todaktodak.model.replyDiary
import com.todaktodak.model.toFromContent
import com.todaktodak.model.useruser
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiaryRoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityDiaryRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDiaryRoomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mail = intent.getStringExtra("email")
        val nick = intent.getStringExtra("nick")

        binding.partnerNickView.text = nick

        getDiaryRoomList(mail!!)

        binding.sendDiaryBtn.setOnClickListener {
            val content = binding.replyDiaryInputBox.text.toString()
            sendDiary(mail, content)
        }
    }

    private fun getDiaryRoomList(mail: String) {
        val call = RetrofitBuilder2.api.getDiaryRoomList(useruser(usersingleton.userEmail, mail))
        call.enqueue(object : Callback<ArrayList<replyDiary>> {

            override fun onResponse(
                call: Call<ArrayList<replyDiary>>,
                response: Response<ArrayList<replyDiary>>
            ) {
                if(response.isSuccessful){
                    setDiaryRoomListView(response.body())
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }
            override fun onFailure(call: Call<ArrayList<replyDiary>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    private fun sendDiary(mail: String, content: String) {
        val call = RetrofitBuilder2.api.sendDiary(toFromContent(mail, usersingleton.userEmail, content))
        call.enqueue(object : Callback<String> {

            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful){
                    getDiaryRoomList(mail)
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    private fun setDiaryRoomListView(body: ArrayList<replyDiary>?) {
        val adapter = DiaryRoomAdapter(body)
        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 1)
        binding.diaryRoomListView.layoutManager = manager
        binding.diaryRoomListView.adapter = adapter
    }
}