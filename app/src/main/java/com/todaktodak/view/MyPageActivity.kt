package com.todaktodak.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.todaktodak.databinding.ActivityMyPageBinding
import com.todaktodak.model.emailnick
import com.todaktodak.retrofit.RetrofitBuilder2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.changeNickBtn.setOnClickListener {
            changeNickDialog()
        }

        binding.PCBtn.setOnClickListener {
            var uri : Uri = Uri.parse("http://www.google.com/maps?q=35.11061, 126.8774")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.requestBtn.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("label", "mccgit2024@gmail.com")
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "복사된 이메일 주소로 문의사항을 보내주세요.", Toast.LENGTH_SHORT).show()
        }

        binding.logoutBtn.setOnClickListener {
            val sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE)

            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            var intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        binding.withdrawBtn.setOnClickListener {
            withdrawConfirmationDialog("email")
        }

        binding.goCalBtn.setOnClickListener {
            var intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
        binding.goListBtn.setOnClickListener {
            var intent = Intent(this, DiaryListActivity::class.java)
            startActivity(intent)
        }
        binding.goSocial.setOnClickListener {

        }
        binding.goSocial.setOnClickListener {
            var intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

    }

    private fun withdrawConfirmationDialog(email: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("확인")
        builder.setMessage("탈퇴시 작성하신 모든 일기 기록이 삭제됩니다. 정말 탈퇴하시겠습니까?")

        builder.setPositiveButton("탈퇴") { dialog, which ->
            withdraw(email)
            val sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE)

            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            var intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton("취소") { dialog, which ->

        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun changeNickDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("변경할 닉네임을 입력해주세요")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("변경") { dialog, which ->
            val enteredText = input.text.toString()
            var info = emailnick("test1", enteredText)
            changeNick(info)
        }

        builder.setNegativeButton("취소") { dialog, which ->

        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun changeNick(info: emailnick) {
        val call = RetrofitBuilder2.api.changeNick(info)
        call.enqueue(object : Callback<String> {

            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    private fun withdraw(email: String) {
        val call = RetrofitBuilder2.api.withdraw(email)
        call.enqueue(object : Callback<String> {

            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                } else {
                    Log.d("RESPONSE ERROR: ", "2")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}