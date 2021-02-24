package com.lxc.recruitment.component.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.R
import com.lxc.recruitment.adapters.LetterAdapter
import com.lxc.recruitment.component.title
import com.lxc.recruitment.databinding.FragmentRecyclerBinding
import com.lxc.recruitment.entity.LetterEntity
import com.lxc.recruitment.viewmodels.UserViewModel


class MessageFragment:Fragment(){

    private val viewModel: UserViewModel by activityViewModels()

    private val messageList = mutableListOf<LetterEntity>()

    private var adapter:LetterAdapter = LetterAdapter(messageList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        this.title("消息")

        viewModel.getReceiver()

        viewModel.actionLiveData.observe(viewLifecycleOwner, Observer {
            when(it.action){
                UserViewModel.VIEW_MODEL_CHANGE_USER_INFO_SUCCESS ->{
                    findNavController().navigate(R.id.action_loginFragment_to_retrieveFragment)
                }
            }
        })

        binding.messageList.adapter =adapter

        viewModel.messageList.observe(viewLifecycleOwner, Observer {
            messageList.clear()
            messageList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }
}