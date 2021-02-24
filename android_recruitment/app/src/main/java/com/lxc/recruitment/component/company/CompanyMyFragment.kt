package com.lxc.recruitment.component.company

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.R
import com.lxc.base.bean.BaseFragment
import com.lxc.recruitment.component.title
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentCompanyMyBinding
import com.lxc.recruitment.databinding.FragmentPersonalMyBinding
import com.lxc.recruitment.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyMyFragment: Fragment(){

    private lateinit var binding: FragmentCompanyMyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.title("我的")
        binding = FragmentCompanyMyBinding.inflate(inflater,container,false)
        binding.company = Constants.COMPANY
        binding.tvInfo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_companyInfoFragment)
        }
        binding.tvResume.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_jobsManageFragment)
        }
        return binding.root
    }

}