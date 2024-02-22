package com.todaktodak.Interface


import com.todaktodak.model.Diary
import com.todaktodak.model.Feedback
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface API {

    @POST("chat")
    fun updateFeedResponse(@Body prompt:String): Call<String>

    @POST("feedback")
    fun getFeedResponse(@Body feedback:Feedback): Call<String>

    @POST("getmsg")
    fun getMsgResponse(@Body text: Diary): Call<String>


}