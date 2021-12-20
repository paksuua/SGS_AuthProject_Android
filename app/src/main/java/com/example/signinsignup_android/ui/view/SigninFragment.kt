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
        binding.tvSignin.setOnClickListener {
            if(inputCheck()){
                viewModel.logIn(
                    SigninRequestDto(
                        binding.edtId.text.toString(), binding.edtPwd.text.toString()
                    )
                )
            }
        }

        binding.tvSigninToSignup.setOnClickListener {
            findNavController().navigate(R.id.navigation_signup)
        }

        viewModel.token.observe(
            viewLifecycleOwner,
            { token ->
                if(token.isNotEmpty()){
                    SigninSignupApplication.prefs.token = token
                    Snackbar.make(binding.root, "로그인 성공했습니다.", Snackbar.LENGTH_LONG).show()
                }else{

                }
            }
        )

        viewModel.isSuccess.observe(
            viewLifecycleOwner,
            {
                if(!it){
                    Snackbar.make(binding.root, "로그인 실패했습니다.", Snackbar.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun inputCheck():Boolean{
        // 빈칸 체크
        if (binding.edtId.text.isNotEmpty() && binding.edtPwd.text.isNotEmpty()) {
            Snackbar.make(binding.root, "입력란을 확인하세요", Snackbar.LENGTH_LONG).show()
            return false
        }

        return true
    }
}