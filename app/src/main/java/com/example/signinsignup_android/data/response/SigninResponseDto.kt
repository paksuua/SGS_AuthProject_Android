package com.example.signinsignup_android.data.response

data class SigninResponseDto(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: SigninData
){
    data class SigninData(
        val userName:String,
        val accessToken: String
    )
}
