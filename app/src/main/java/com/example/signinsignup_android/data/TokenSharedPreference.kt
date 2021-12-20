package com.example.signinsignup_android.data

import android.content.Context
import android.content.SharedPreferences

class TokenSharedPreference (context: Context) {

    val PREFS_FILENAME = "prefs"
    var PREF_KEY_TOKEN = "token"
    var prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var token: String
        get() = prefs.getString(PREF_KEY_TOKEN, "")?:""
        set(value) = prefs.edit().putString(PREF_KEY_TOKEN, value).apply()
}