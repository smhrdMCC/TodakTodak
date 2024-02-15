package com.example.project.VO

import com.google.gson.annotations.SerializedName

data class feedbackVO(
    @SerializedName("feedback_seq") var feedbackSeq: Int? = null,
    @SerializedName("diary_seq") var diarySeq: Int? = null,
    @SerializedName("emotion_classification") var emotionClassification: String? = null,
    @SerializedName("ai_recommendation") var aiRecommendation: String? = null,
    @SerializedName("created_at") var createdAt: String? = null
)
