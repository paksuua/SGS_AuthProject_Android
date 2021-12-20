package com.example.signinsignup_android.util

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    val PREFS_FILENAME = "prefs"
    var PREF_KEY_ID = "token"
    var prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var token: String
    get() = prefs.getString(PREF_KEY_ID, "")?:""
    set(value) = prefs.edit().putString(PREF_KEY_ID, value).apply()

}