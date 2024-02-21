package com.todaktodak.Interface

import com.todaktodak.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface API {
    // kakao login
    @POST("join")
    fun getLoginResponse(@Body user: User): Call<String>

    @POST("chat")
    fun updateFeedResponse(@Body prompt:String): Call<String>

    @POST("feedback")
    fun getFeedResponse(@Body feedback:String): Call<String>

//    @POST("getmsg")
//    fun getMsgResponse(@Body text: Diary): Call<String>



}