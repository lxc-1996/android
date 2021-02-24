package com.lxc.recruitment.component.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.lxc.recruitment.adapters.AuditAdapter
import com.lxc.recruitment.component.title
import com.lxc.recruitment.databinding.*
import com.lxc.recruitment.entity.JobEntity
import com.lxc.recruitment.viewmodels.AdminViewModel


class AdminJobManagerFragment: Fragment(){

    private val viewModel: AdminViewModel by activityViewModels()

    private val jobList = mutableListOf<JobEntity>()

    private var adapter: AuditAdapter = AuditAdapter(jobList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        this.title("职位管理")
        viewModel.auditList()
        binding.messageList.adapter =adapter

        viewModel.auditList.observe(viewLifecycleOwner, Observer {
            jobList.clear()
            jobList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }

}