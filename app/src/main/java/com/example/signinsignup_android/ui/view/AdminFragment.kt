package com.example.signinsignup_android.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.signinsignup_android.databinding.FragmentAdminBinding
import com.example.signinsignup_android.ui.viewmodel.AdminViewModel


class AdminFragment: Fragment() {
    private val viewModel by viewModels<AdminViewModel>()
    private val binding by lazy { FragmentAdminBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initUI()
        return binding.root
    }

    private fun initUI() {
        // TODO: 1. 초기화면에서 모든 유저의 리스트가 나타난다
        // TODO: 2-1. 특정 유저를 롱클릭을 통해 삭제 메뉴를 띄운다
        // TODO: 2-2. 삭제 버튼을 누르면 유저 정보가 삭제된
    }
}