package com.todaktodak.Interface

import com.todaktodak.model.datemailVO
import com.todaktodak.model.emotionContentVO
import com.todaktodak.model.feedbackVO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {

    @POST("join")
    fun getLoginResponse(@Body user: User): Call<String>

    @POST("chat")
    fun updateFeedResponse(@Body prompt:String): Call<String>

    @POST("feedback")
    fun getFeedResponse(@Body feedback:String): Call<String>

    @POST("emotion")
    fun getEmotion(@Body info: datemailVO): Call<ArrayList<feedbackVO>>

    @POST("dairyList")
    fun getDiaryList(@Body info: datemailVO): Call<ArrayList<emotionContentVO>>

}