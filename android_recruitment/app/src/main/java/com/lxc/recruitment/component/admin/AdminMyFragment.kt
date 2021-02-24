package com.lxc.recruitment.component.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.R
import com.lxc.base.bean.BaseFragment
import com.lxc.recruitment.component.title
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentAdminMyBinding
import com.lxc.recruitment.databinding.FragmentCompanyMyBinding
import com.lxc.recruitment.databinding.FragmentPersonalMyBinding
import com.lxc.recruitment.viewmodels.UserViewModel


class AdminMyFragment: Fragment(){

    private lateinit var binding: FragmentAdminMyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.title("我的")
        binding = FragmentAdminMyBinding.inflate(inflater,container,false)
        binding.admin = Constants.ADMIN
        binding.tvInfo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_adminInfoFragment)
        }
        binding.tvUser.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_adminUserManagerFragment)
        }
        binding.tvResume.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_adminJobManagerFragment)
        }
        return binding.root
    }

}