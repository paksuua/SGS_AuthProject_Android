package com.example.signinsignup_android.ui.viewmodel

import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signinsignup_android.data.SignDataSource
import com.example.signinsignup_android.data.request.SigninRequestDto
import com.example.signinsignup_android.data.response.SigninResponseDto
import com.example.signinsignup_android.network.getNetworkInstance
import com.example.signinsignup_android.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class SigninViewModel : ViewModel() {
    // 로그인 실행 여부
    private val _isSuccess: SingleLiveEvent<Boolean> =  SingleLiveEvent()
    val isSuccess: LiveData<Boolean> = _isSuccess

    // 사용자 이름
    private val _userName: MutableLiveData<String> = MutableLiveData(null)
    val userName: LiveData<String> = _userName

    // login token
    private val _token: MutableLiveData<String> = MutableLiveData("")
    val token: LiveData<String> = _token

    // login 요청
    private val _confirm: MutableLiveData<Boolean> = MutableLiveData(false)
    val confirm: LiveData<Boolean> = _confirm

    fun logIn(request: SigninRequestDto) {
         SignDataSource().postSignin(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<SigninResponseDto>() {
                override fun onSuccess(t: SigninResponseDto) {
                    var data: SigninResponseDto.SigninData = t!!.data

                    Log.d("SignInViewModel", "response " + data)
                    _userName.postValue(data.userName)
                    _token.postValue(data.accessToken)
                    _isSuccess.postValue(true)
                }

                override fun onError(e: Throwable) {
                    Log.d("SignInViewModel", "Error " + e)
                    _isSuccess.postValue(false)
                }
            })
    }
}