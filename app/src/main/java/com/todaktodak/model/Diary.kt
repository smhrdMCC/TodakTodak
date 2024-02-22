package com.example.mccproject.Model

import com.google.gson.annotations.SerializedName


data class Diary(

    @SerializedName("user_email")        var userEmail: String? = null,
    @SerializedName("diary_content")     var diaryContent: String? = null

)


