package com.todaktodak.Interface

import com.todaktodak.model.datemailVO
import com.todaktodak.model.emotionContentVO
import com.todaktodak.model.emotiondate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface API {

    @POST("emotion")
    fun getEmotion(@Body info: datemailVO): Call<ArrayList<emotiondate>>

    @POST("dairyList")
    fun getDiaryList(@Body info: datemailVO): Call<ArrayList<emotionContentVO>>



}