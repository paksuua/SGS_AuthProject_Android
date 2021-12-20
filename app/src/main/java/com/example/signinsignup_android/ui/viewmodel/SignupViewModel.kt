package com.example.signinsignup_android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signinsignup_android.data.SignDataSource
import com.example.signinsignup_android.data.request.SignupRequestDto
import com.example.signinsignup_android.data.response.SignupResponseDto
import com.example.signinsignup_android.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SignupViewModel : ViewModel() {
    // 회원가입 성공 여부
    private val _isSuccess: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun signUp(request: SignupRequestDto) {
        SignDataSource().postSignup(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<SignupResponseDto>() {
                override fun onSuccess(t: SignupResponseDto) {
                    var data: SignupResponseDto.SignupData = t!!.data

                    Log.d("SignUnViewModel", "response " + data)

                    _isSuccess.postValue(true)
                }

                override fun onError(e: Throwable) {
                    Log.d("SignUnViewModel", "Error " + e)
                    _isSuccess.postValue(true)
                }
            })
    }
    
}