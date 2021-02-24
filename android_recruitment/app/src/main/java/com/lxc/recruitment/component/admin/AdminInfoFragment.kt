package com.lxc.recruitment.component.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.component.title
import com.lxc.recruitment.databinding.FragmentAdminInfoBinding
import com.lxc.recruitment.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * 管理员信息编辑
 */
@AndroidEntryPoint
class AdminInfoFragment:Fragment(){

    private lateinit var binding: FragmentAdminInfoBinding

    private val viewModel: AdminViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminInfoBinding.inflate(inflater, container, false)
        binding.admin = viewModel.admin
        this.title("我的")
        binding.btnSave.setOnClickListener {
            viewModel.addAdminInfo()
            findNavController().navigateUp()
        }
        return binding.root
    }

}
