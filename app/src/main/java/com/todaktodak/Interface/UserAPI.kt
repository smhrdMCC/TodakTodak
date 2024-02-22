package com.todaktodak.Interface

import com.example.mccproject.Model.FindDiary
import com.todaktodak.model.User
import com.todaktodak.model.datemailVO
import com.todaktodak.model.emotionContentVO
import com.todaktodak.model.emotiondate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface API {
    // login
    @POST("join")
    fun getLoginResponse(@Body user: User): Call<String>

    @POST("chat")
    fun updateFeedResponse(@Body prompt:String): Call<String>

    @POST("feedback")
    fun getFeedResponse(@Body feedback:String): Call<String>


    @POST("diary")
    fun doGetDiary(@Body diary: String): Call<String>

    @POST("findDiary")
    fun findDiary(@Body diary: String) : Call<String>

    @POST("emotion")
    fun getEmotion(@Body info: datemailVO): Call<ArrayList<emotiondate>>

    @POST("dairyList")
    fun getDiaryList(@Body info: datemailVO): Call<ArrayList<emotionContentVO>>

    @POST("getmsg")
    fun getMsgResponse(@Body text: FindDiary): Call<String>

}