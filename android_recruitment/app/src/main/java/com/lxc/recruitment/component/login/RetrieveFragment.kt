package com.lxc.recruitment.component.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.lxc.base.base.BaseFragment
import com.lxc.base.base.BaseViewModel
import com.lxc.recruitment.R
import com.lxc.base.util.toastK
import com.lxc.recruitment.databinding.FragmentRetrieveBinding
import com.lxc.recruitment.viewmodels.UserViewModel


class RetrieveFragment : BaseFragment() {

    private lateinit var binding: FragmentRetrieveBinding

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetrieveBinding.inflate(inflater, container, false)
        binding.user = viewModel.userBean
        binding.btnChange.setOnClickListener {
            viewModel.findPassword()
        }

        viewModel.actionLiveData.observe(viewLifecycleOwner, Observer {
            when(it.action){
                UserViewModel.VIEW_MODEL_CHANGE_PASSWORD_SUCCESS ->{
                    toastK("密码找回成功！")
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        })

        return binding.root
    }

}