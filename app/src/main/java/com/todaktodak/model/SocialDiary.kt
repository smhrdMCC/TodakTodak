package com.todaktodak.model

data class replyDiary(
    var email: String,
    var nick: String,
    var content: String,
    var date: String
)

data class randomDiary(
    var seq: Long,
    var fromUser: String,
    var nick: String,
    var content: String,
    var date: String
)

data class useruser(
    var userA: String,
    var userB: String
)

data class toFromContent(
    var toUser: String,
    var fromUser: String,
    var content: String
)

data class linkDiary(
    var toUser: String?,
    var fromUser: String?,
    var content: String?,
    var inputcontent: String?
)