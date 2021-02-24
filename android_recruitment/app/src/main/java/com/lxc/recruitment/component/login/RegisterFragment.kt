package com.lxc.recruitment.component.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.R
import com.lxc.base.util.toastK
import com.lxc.recruitment.databinding.FragmentRegisterBinding
import com.lxc.recruitment.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        binding.user = viewModel.userBean

        binding.btnLogin.setOnClickListener {
            viewModel.register()
        }

        binding.rbPersonal.setOnClickListener {
            viewModel.userBean.type = UserEntity.PERSONAL
        }

        binding.rbCompany.setOnClickListener {
            viewModel.userBean.type = UserEntity.COMPANY
        }

        viewModel.actionLiveData.observe(viewLifecycleOwner, Observer {
            when(it.action){
                UserViewModel.VIEW_MODEL_REGISTER_SUCCESS ->{
                    toastK("账户注册成功！")
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        })
        return  binding.root
    }
}