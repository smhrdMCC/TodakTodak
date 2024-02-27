package com.todaktodak.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.R
import com.todaktodak.adapter.DiaryRoomAdapter
import com.todaktodak.adapter.ReplyDiaryListAdapter
import com.todaktodak.databinding.ActivityDiaryRoomBinding
import com.todaktodak.model.diaryInRoom
import com.todaktodak.model.mailmail
import com.todaktodak.model.replyDiary
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

        val mail = intent.getStringExtra("mail")
        getDiaryRoomList(mail!!)

    }

    private fun getDiaryRoomList(mail: String) {
        val call = RetrofitBuilder2.api.getDiaryRoomList(mailmail(usersingleton.userEmail, mail))
        call.enqueue(object : Callback<ArrayList<diaryInRoom>> {

            override fun onResponse(
                call: Call<ArrayList<diaryInRoom>>,
                response: Response<ArrayList<diaryInRoom>>
            ) {
                if(response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                    setDiaryRoomListView(response.body())
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            override fun onFailure(call: Call<ArrayList<diaryInRoom>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    private fun setDiaryRoomListView(body: ArrayList<diaryInRoom>?) {
        val adapter = DiaryRoomAdapter(body)
        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 1)
        binding.diaryRoomListView.layoutManager = manager
        binding.diaryRoomListView.adapter = adapter
    }
}