package com.example.signinsignup_android.data.response

data class SignupResponseDto(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: SignupData
){
    data class SignupData(
        val userIdx:Int,
        val id:String,
        val name:String,
        val email:String
    )
}
