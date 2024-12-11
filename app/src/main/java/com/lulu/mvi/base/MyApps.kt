package com.lulu.mvi.base

import android.app.Application
import com.drake.net.BuildConfig
import com.drake.net.NetConfig
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setErrorHandler
import java.util.concurrent.TimeUnit

class MyApps : Application() {
    override fun onCreate() {
        super.onCreate()

        NetConfig.initialize("", this) {
            // 超时配置, 默认是10秒, 设置太长时间会导致用户等待过久
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            setDebug(BuildConfig.DEBUG)
        }
    }
}