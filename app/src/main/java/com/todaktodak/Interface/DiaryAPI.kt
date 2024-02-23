package com.todaktodak.Interface

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DiaryAPI {
    @POST("diary")
    fun findDiary(@Body diary: String) : Call<String>
}