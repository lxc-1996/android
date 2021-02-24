package com.lxc.recruitment.component.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.R
import com.lxc.base.util.toastK
import com.lxc.recruitment.component.MainActivity
import com.lxc.recruitment.databinding.FragmentLoginBinding
import com.lxc.recruitment.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.et_account

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.user = viewModel.userBean
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        viewModel.dict()

        binding.btnRetrieve.setOnClickListener {
            if (et_account.text.isBlank()) {
                toastK("请先输入账号")
            } else {
                viewModel.getUserInfo()
            }
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }

        viewModel.actionLiveData.observe(viewLifecycleOwner, Observer {
            when(it.action){
                UserViewModel.VIEW_MODEL_LOGIN_SUCCESS ->{
                    startActivity(Intent(activity,MainActivity::class.java))
                }
                UserViewModel.VIEW_MODEL_CHANGE_USER_INFO_SUCCESS ->{
                    findNavController().navigate(R.id.action_loginFragment_to_retrieveFragment)
                }
            }
        })

        Handler().postDelayed({binding.btnLogin.performClick()},1000)
        return binding.root
    }
}

