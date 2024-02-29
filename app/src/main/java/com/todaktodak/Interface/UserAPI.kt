package com.todaktodak.Interface

import com.example.mccproject.Model.Diary
import com.todaktodak.model.User
import com.todaktodak.model.datemailVO
import com.todaktodak.model.emailnick
import com.todaktodak.model.emotionContentVO
import com.todaktodak.model.emotiondate
import com.todaktodak.model.linkDiary
import com.todaktodak.model.randomDiary
import com.todaktodak.model.replyDiary
import com.todaktodak.model.seqcont
import com.todaktodak.model.toFromContent
import com.todaktodak.model.useruser
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

    @POST("getReplyDiaryList")
    fun getReplyDiaryList(@Body userEmail: String): Call<ArrayList<replyDiary>>

    @POST("getRandomDiaryList")
    fun getRandomDiaryList(@Body userEmail: String): Call<ArrayList<randomDiary>>

    @POST("getFeedBackMessage")
    fun getFeedBackMessage(@Body feedback : String) : Call<String>

    @POST("getDiaryRoomList")
    fun getDiaryRoomList(@Body info: useruser): Call<ArrayList<replyDiary>>

    @POST("sendDiary")
    fun sendDiary(@Body info: toFromContent): Call<String>

    @POST("linkUserDiary")
    fun linkUserDiary(@Body info: linkDiary): Call<String>

    @POST("openDiary")
    fun sendOpenDiary(@Body info: String): Call<String>

    @POST("alterDiary")
    fun alterDiary(@Body info: String): Call<String>
//    @POST("sendBert")
//    fun sendBert(@Body diaryCon: String): Call<String>
//
//    @POST("getBert")
//    fun getBert(@Body diaryCon: String): Call<String>
}

