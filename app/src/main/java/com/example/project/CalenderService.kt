package com.example.project

import com.example.project.VO.feedbackVO
import com.example.project.VO.datemailVO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CalenderService {
    @POST("emotion")
    fun getEmotion(@Body info: datemailVO): Call<List<feedbackVO>>
}