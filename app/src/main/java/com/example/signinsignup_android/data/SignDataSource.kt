package com.example.signinsignup_android.data

import com.example.signinsignup_android.data.request.SigninRequestDto
import com.example.signinsignup_android.data.request.SignupRequestDto
import com.example.signinsignup_android.data.response.SigninResponseDto
import com.example.signinsignup_android.data.response.SignupResponseDto
import com.example.signinsignup_android.network.getNetworkInstance
import io.reactivex.Single

class SignDataSource {
    var service = getNetworkInstance()

    // 회원가입
    fun postSignup(signupData: SignupRequestDto): Single<SignupResponseDto> {
        return service.postSignup(signupData)
    }

    // 로그인
    fun postSignin(signInRequestDto: SigninRequestDto): Single<SigninResponseDto> {
        return service.postSignin(signInRequestDto)
    }
}