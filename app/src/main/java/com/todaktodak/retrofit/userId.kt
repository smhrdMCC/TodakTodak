package com.todaktodak.retrofit

import com.todaktodak.model.emotiondate

object usersingleton{
    var userEmail: String = ""
    var userNick: String = ""
}
object emotionsingleton{
    lateinit var list: ArrayList<emotiondate>
}

object DiaryTextSingleTon{
    var diaryText : String = ""
}

object DiarySeqSingleton{
    var diarySeq : String = ""
}