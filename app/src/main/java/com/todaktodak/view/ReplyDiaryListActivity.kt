package com.todaktodak.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.adapter.ReplyDiaryListAdapter
import com.todaktodak.databinding.ActivityReplyDiaryListBinding
import com.todaktodak.model.replyDiary
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyDiaryListActivity : AppCompatActivity() {
    lateinit var binding: ActivityReplyDiaryListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReplyDiaryListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 상단 버튼
        binding.replyDiaryListBtn.setOnClickListener {
            var intent = Intent(this, ReplyDiaryListActivity::class.java)
            startActivity(intent)
        }
        binding.randomDiaryListBtn.setOnClickListener {
            var intent = Intent(this, RandomDiaryListActivity::class.java)
            startActivity(intent)
        }

        // 나에게 온 일기 목록 불러와서 출력
        getReplyDiaryList()

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

    private fun getReplyDiaryList() {
        val call = RetrofitBuilder2.api.getReplyDiaryList(usersingleton.userEmail)
        call.enqueue(object : Callback<ArrayList<replyDiary>> {

            override fun onResponse(
                call: Call<ArrayList<replyDiary>>,
                response: Response<ArrayList<replyDiary>>
            ) {
                if(response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                    setReplyDiaryListView(response.body())
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            override fun onFailure(call: Call<ArrayList<replyDiary>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
    private fun setReplyDiaryListView(body: ArrayList<replyDiary>?) {
        val adapter = ReplyDiaryListAdapter(body, this)
        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 1)
        binding.replyDiaryList.layoutManager = manager
        binding.replyDiaryList.adapter = adapter
    }
    fun onItemClick(email: String?) {
        var intent = Intent(this, WriteDiaryActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
    }
}