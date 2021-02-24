package com.lxc.recruitment.component.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.R
import com.lxc.recruitment.adapters.NewsManageAdapter
import com.lxc.recruitment.component.title
import com.lxc.recruitment.databinding.FragmentAdminHomeBinding
import com.lxc.recruitment.entity.NewsEntity
import com.lxc.recruitment.viewmodels.AdminViewModel


class AdminHomeFragment:Fragment(){

    private val viewModel: AdminViewModel by activityViewModels()

    private val messageList = mutableListOf<NewsEntity>()

    private var adapter = NewsManageAdapter(messageList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        this.title("新闻")
        viewModel.getNews()
        binding.messageList.adapter =adapter
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            messageList.clear()
            messageList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        binding.btnAddNews.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newsInfoFragment)
        }
        return binding.root
    }
}