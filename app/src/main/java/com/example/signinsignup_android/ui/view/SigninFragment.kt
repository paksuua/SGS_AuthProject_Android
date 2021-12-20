package com.example.signinsignup_android.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.signinsignup_android.R
import com.example.signinsignup_android.SigninSignupApplication
import com.example.signinsignup_android.data.request.SigninRequestDto
import com.example.signinsignup_android.databinding.FragmentSigninBinding
import com.example.signinsignup_android.ui.viewmodel.SigninViewModel
import com.google.android.material.snackbar.Snackbar


class SigninFragment: Fragment() {
    private val viewModel by viewModels<SigninViewModel>()
    private val binding by lazy { FragmentSigninBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initUI()
        return binding.root
    }

    private fun initUI() {
        // 로그인
        binding.tvSignin.setOnClickListener {
            if(inputCheck()){
                viewModel.logIn(
                    SigninRequestDto(
                        binding.edtId.text.toString(), binding.edtPwd.text.toString()
                    )
                )
            }
        }

        // 회원가입 화면으로 이동
        binding.tvSigninToSignup.setOnClickListener {
            findNavController().navigate(R.id.navigation_signup)
        }

        viewModel.isSuccess.observe(
            viewLifecycleOwner,
            {
                if(!it){
                    Snackbar.make(binding.root, "로그인 실패했습니다.", Snackbar.LENGTH_LONG).show()
                }else if(viewModel.token.value != ""){
                    SigninSignupApplication.prefs.token = viewModel.token.value.toString()
                    Snackbar.make(binding.root, "로그인 성공했습니다.", Snackbar.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun inputCheck():Boolean{
        var result = true
        // 빈칸 체크
        if (binding.edtId.text.isNullOrEmpty() || binding.edtPwd.text.isNullOrEmpty()) {
            Snackbar.make(binding.root, "입력란을 확인하세요", Snackbar.LENGTH_LONG).show()
            result = false
        }
        return result
    }
}