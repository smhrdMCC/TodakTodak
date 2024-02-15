package com.example.project

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 레트로핏 객체 싱글톤으로 생성
object CalenderEmotion {
    const val baseUrl = "http://10.0.2.2:8088/boot/"
    val gson : Gson = GsonBuilder()
        .setLenient()
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(CalenderService::class.java)!!
}