package com.todaktodak.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.todaktodak.Interface.API
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


// 플라스크 서버
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
object  RetrofitBuilder2 {
    var api: API
    init{
        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8100/") // 요청 보내는 API 서버 url. /로 끝나야 함함
            .addConverterFactory(GsonConverterFactory.create(gson)) // Gson을 역직렬화
            .build()
        api = retrofit.create(API::class.java)
    }
}

interface BertAPI {
    @POST("sendBert")
    fun sendDataToFlask(@Body data: String): Call<String>
}

object RetrofitBuilderBert {
    private const val BASE_URL = "http://10.0.2.2:8120/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BertAPI by lazy {
        retrofit.create(BertAPI::class.java)
    }
}