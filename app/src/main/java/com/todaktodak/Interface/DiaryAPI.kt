package com.todaktodak.Interface

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DiaryAPI {

    @POST("diary")
    // 함수를 선언만 하고 통신할 때 필요한 어떤 코드도 담지 않는다.
    fun doGetDiary(@Body diary: String): Call<String>

    @POST("diary")
    fun findDiary(@Body diary: String) : Call<String>

}