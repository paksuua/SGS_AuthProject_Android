package com.example.signinsignup_android.network

import com.example.signinsignup_android.data.request.SigninRequestDto
import com.example.signinsignup_android.data.request.SignupRequestDto
import com.example.signinsignup_android.data.response.SigninResponseDto
import com.example.signinsignup_android.data.response.SignupResponseDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkInterface {
    // 1. 회원가입
    @POST("/user/signup")
    fun postSignup(
        @Body signUpRequestDto: SignupRequestDto,
    ): Single<SignupResponseDto>

    // 2. 로그인
    @POST("/user/signin")
    fun postSignin(
        @Body signInRequestDto: SigninRequestDto,
    ): Single<SigninResponseDto>
}