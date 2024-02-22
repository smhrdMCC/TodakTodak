package com.todaktodak.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.todaktodak.Interface.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 플라스트 서버
object RetrofitBuilder {
    var api: API
    init{
        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/") // 요청 보내는 API 서버 url. /로 끝나야 함함
            .addConverterFactory(GsonConverterFactory.create()) // Gson을 역직렬화
            .build()
        api = retrofit.create(API::class.java)
    }
}

// 스프링부트 서버
object RetrofitBuilder2 {
    var api: API
    init{
        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8100/") // 요청 보내는 API 서버 url. /로 끝나야 함함
            .addConverterFactory(GsonConverterFactory.create()) // Gson을 역직렬화
            .build()
        api = retrofit.create(API::class.java)
    }
}