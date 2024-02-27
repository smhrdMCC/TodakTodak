package com.todaktodak.model

data class replyDiary(
    var email: String,
    var nick: String,
    var content: String,
    var date: String
)

data class mailmail(
    var toEmail: String,
    var fromEmail: String
)

data class diaryInRoom(
    var toEmail: String,
    var fromEmail: String,
    var content: String,
    var date: String
)