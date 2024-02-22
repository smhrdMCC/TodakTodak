package com.todaktodak.Interface

import com.example.mccproject.Model.FindDiary
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

    @POST("getmsg")
    fun getMsgResponse(@Body text: FindDiary): Call<String>
    @POST("diary")
    // 함수를 선언만 하고 통신할 때 필요한 어떤 코드도 담지 않는다.
    fun doGetDiary(@Body diary: String): Call<String>


}