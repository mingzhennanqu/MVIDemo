package com.lulu.mvi.base

import com.drake.net.convert.JSONConvert
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.lang.reflect.Type

class GsonConvert : JSONConvert(code = "code", message = "msg", success = "200") {
    private val gson = GsonBuilder().serializeNulls().create()
    override fun <R> String.parseBody(succeed: Type): R? {
        val data = JSONObject(this).getString("data")
        return gson.fromJson(data, succeed)
    }
}

class GsonConvert2 : JSONConvert(code = "code", message = "msg", success = "200") {
    private val gson = GsonBuilder().serializeNulls().create()
    override fun <R> String.parseBody(succeed: Type): R? {
        val data = JSONObject(this).getString("mp4_video")
        return gson.fromJson(data, succeed)
    }
}