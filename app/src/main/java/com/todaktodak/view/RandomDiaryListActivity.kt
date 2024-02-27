package com.todaktodak.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.adapter.RandomDiaryListAdapter
import com.todaktodak.databinding.ActivityRandomDiaryListBinding
import com.todaktodak.model.randomDiary
import com.todaktodak.retrofit.RetrofitBuilder2
import com.todaktodak.retrofit.usersingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomDiaryListActivity : AppCompatActivity() {
    lateinit var binding: ActivityRandomDiaryListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRandomDiaryListBinding.inflate(layoutInflater)
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

        getRandomDiaryList()

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

    private fun getRandomDiaryList() {
        val call = RetrofitBuilder2.api.getRandomDiaryList(usersingleton.userEmail)
        call.enqueue(object : Callback<ArrayList<randomDiary>> {

            override fun onResponse(
                call: Call<ArrayList<randomDiary>>,
                response: Response<ArrayList<randomDiary>>
            ) {
                if(response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                    setRandomDiaryListView(response.body())
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            override fun onFailure(call: Call<ArrayList<randomDiary>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    private fun setRandomDiaryListView(body: ArrayList<randomDiary>?) {
        val adapter = RandomDiaryListAdapter(body, this)
        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 1)
        binding.randomDiaryList.layoutManager = manager
        binding.randomDiaryList.adapter = adapter
    }

    fun onItemClick(email: String?, nick: String?, content: String?) {
        var intent = Intent(this, RandomDiaryRoomActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("nick", nick)
        intent.putExtra("content", content)
        startActivity(intent)
    }
}