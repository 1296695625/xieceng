package com.example.xieceng

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*

//https://mock.mengxuegu.com/mock/5f2ccaaafd0fa244c4c55f09/example https://mock.mengxuegu.com/mock/5f2ccaaafd0fa244c4c55f09/example/getStr
private val service: NetWorkUtil by lazy {
    val okHttpClient = OkHttpClient.Builder().addInterceptor(SkipNetworkInterceptor()).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://mock.mengxuegu.com/mock/5f2ccaaafd0fa244c4c55f09/example/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
    retrofit.create(NetWorkUtil::class.java)
}

fun getNetWorkSingleton() = service;
interface NetWorkUtil {
    @GET("getStr")
     fun getStr(): Call<String>
}