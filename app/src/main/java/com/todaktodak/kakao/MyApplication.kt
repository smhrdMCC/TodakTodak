package com.todaktodak.kakao

import android.app.Application
import android.util.Log

import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.Constants.TAG

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "49901b6cc756bd95d67c1f267bad3995")

        var keyHash = Utility.getKeyHash(this)
        Log.e(TAG, "해시 키 값 : ${keyHash}")
    }
}