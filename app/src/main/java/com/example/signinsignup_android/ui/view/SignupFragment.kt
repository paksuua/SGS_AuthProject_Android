package com.example.signinsignup_android.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.signinsignup_android.R
import com.example.signinsignup_android.data.request.SignupRequestDto
import com.example.signinsignup_android.databinding.FragmentSignupBinding
import com.example.signinsignup_android.ui.viewmodel.SignupViewModel
import com.google.android.material.snackbar.Snackbar

class SignupFragment: Fragment() {
    private val viewModel by viewModels<SignupViewModel>()
    private val binding by lazy { FragmentSignupBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initUI()
        return binding.root
    }

    private fun initUI() {
        // 뒤로가기
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // 회원가입
        binding.tvSignin.setOnClickListener {
            if(inputCheck()){
                viewModel.signUp(
                    SignupRequestDto(
                        binding.edtId.text.toString(),
                        binding.edtName.text.toString(),
                        binding.edtPwd.text.toString(),
                        binding.edtEmail.text.toString()
                    )
                )
            }
        }

        // 회원가입이 성공하면
        viewModel.isSuccess.observe(
            viewLifecycleOwner,
            {
                if(it){ // 회원가입 성공 -> 로그인 화면으로 다시 이동
                    Snackbar.make(binding.root, "가입되셨습니다. 로그인 화면으로 이동합니다", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.navigation_login)
                }else{
                    Snackbar.make(binding.root, "가입되셨습니다.", Snackbar.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun inputCheck():Boolean{
        var result = true
        // 빈칸 체크
        if (binding.edtName.text.isNullOrEmpty() || binding.edtId.text.isNullOrEmpty()
            || binding.edtEmail.text.isNullOrEmpty() || binding.edtPwd.text.isNullOrEmpty()
            || binding.edtPwd.text.isNullOrEmpty()) {
            Snackbar.make(binding.root, "입력란을 확인하세요", Snackbar.LENGTH_LONG).show()
            result = false
        }

        // 비밀번호 확인
        if ( binding.edtPwd.text.toString() != binding.edtPwd.text.toString()) {
            Snackbar.make(binding.root, "비밀번호를을 확인하세요", Snackbar.LENGTH_LONG).show()
            result = false
        }
        return result
    }
}