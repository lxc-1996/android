package com.lxc.recruitment.component.personal

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
import com.lxc.recruitment.databinding.FragmentPersonalMyBinding
import com.lxc.recruitment.viewmodels.UserViewModel


class PersonalMyFragment: Fragment(){

    private lateinit var binding: FragmentPersonalMyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.title("我的")
        binding = FragmentPersonalMyBinding.inflate(inflater,container,false)
        binding.personal = Constants.PERSONAL
        binding.tvInfo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_personalInfoFragment)
        }
        binding.tvResume.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_resumeManageFragment)
        }
        binding.tvCollect.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_resumeManageFragment)
        }
        return binding.root
    }

}