package com.lxc.recruitment.component.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lxc.recruitment.component.title
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentCompanyInfoBinding
import com.lxc.recruitment.databinding.FragmentPersonalInfoBinding
import com.lxc.recruitment.viewmodels.CompanyViewModel
import com.lxc.recruitment.viewmodels.PersonalViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * 公司信息编辑
 */
@AndroidEntryPoint
class CompanyInfoFragment:Fragment(){

    private lateinit var binding: FragmentCompanyInfoBinding

    private val viewModel: CompanyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.title("个人信息")
        binding = FragmentCompanyInfoBinding.inflate(inflater, container, false)
        viewModel.company = Constants.COMPANY
        binding.company = viewModel.company

        binding.btnSave.setOnClickListener {
            viewModel.addCompanyInfo()
        }
        return binding.root
    }

}
