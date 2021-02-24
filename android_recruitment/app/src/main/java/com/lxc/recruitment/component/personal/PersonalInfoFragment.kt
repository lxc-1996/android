package com.lxc.recruitment.component.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lxc.recruitment.component.title
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentPersonalInfoBinding
import com.lxc.recruitment.viewmodels.PersonalViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * 个人信息编辑
 */
@AndroidEntryPoint
class PersonalInfoFragment:Fragment(){

    private lateinit var binding: FragmentPersonalInfoBinding

    private val viewModel: PersonalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.title("个人信息")
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        viewModel.personal = Constants.PERSONAL
        binding.personal = viewModel.personal

        binding.btnSave.setOnClickListener {
            viewModel.addPersonalInfo()
        }
        return binding.root
    }

}
