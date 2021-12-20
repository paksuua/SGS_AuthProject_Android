package com.example.signinsignup_android.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://1.11.1.1"

var client: OkHttpClient?= null
var retrofit: Retrofit ?= null
var networkInterface: NetworkInterface ?= null

fun networkInit() {
    var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client!!)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    networkInterface = retrofit!!.create(NetworkInterface::class.java)
}

fun getNetworkInstance(): NetworkInterface {
    if(networkInterface == null){
        networkInit()
    }
    return networkInterface!!
}