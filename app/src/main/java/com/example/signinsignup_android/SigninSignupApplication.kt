package com.example.signinsignup_android

import android.app.Application
import com.example.signinsignup_android.util.MySharedPreferences

class SigninSignupApplication : Application() {
    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var prefs : MySharedPreferences
    }
}