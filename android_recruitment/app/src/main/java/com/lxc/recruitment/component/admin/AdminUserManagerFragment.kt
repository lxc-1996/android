package com.lxc.recruitment.component.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.adapters.UserManageAdapter
import com.lxc.recruitment.component.title
import com.lxc.recruitment.databinding.FragmentRecyclerBinding
import com.lxc.recruitment.viewmodels.AdminViewModel


class AdminUserManagerFragment: Fragment(){

    private val viewModel: AdminViewModel by activityViewModels()

    private val userList = mutableListOf<UserEntity>()

    private var adapter: UserManageAdapter = UserManageAdapter(userList)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        this.title("用户管理")
        viewModel.userList()
        binding.messageList.adapter =adapter

        viewModel.userList.observe(viewLifecycleOwner, Observer {
            userList.clear()
            userList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }

}