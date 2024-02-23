package com.todaktodak.model

import com.google.gson.annotations.SerializedName

data class feedbackVO(
    @SerializedName("feedback_seq") var feedbackSeq: Int? = null,
    @SerializedName("diary_seq") var diarySeq: Int? = null,
    @SerializedName("emotion_classification") var emotionClassification: String? = null,
    @SerializedName("ai_recommendation") var aiRecommendation: String? = null,
    @SerializedName("created_at") var createdAt: String? = null
)

data class emotiondate(
    var emotion: String? = null,
    var createdAt: String? = null
)

data class datemailVO(
    var date: String,
    var email: String
)

data class emailnick(
    var email: String,
    var nick: String
)

data class emotionContentVO(
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("emotion_classification") var emotionClassification: String? = null,
    @SerializedName("diary_content") var diaryContent: String? = null
)