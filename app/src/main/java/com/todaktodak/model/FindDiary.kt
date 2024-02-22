package com.example.mccproject.Model

import com.google.gson.annotations.SerializedName

data class FindDiary(
    @SerializedName("user_email")       val userEmail:String,
    @SerializedName("diary_content")    val diaryContent: String,
    @SerializedName("created_at")       val createdAt:String
)