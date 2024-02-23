package com.todaktodak.Interface

import com.todaktodak.model.User
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

    @POST("changeNick")
    fun changeNick(@Body info: emailnick): Call<String>

    @POST("getmsg")
    fun getMsgResponse(@Body info: datemailVO): Call<List<seqcont>>

    @POST("withdraw")
    fun withdraw(@Body userEmail: String): Call<String>
}