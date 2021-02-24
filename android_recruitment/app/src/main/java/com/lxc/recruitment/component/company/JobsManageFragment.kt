package com.lxc.recruitment.component.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.R
import com.lxc.base.bean.BaseFragment
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.recruitment.adapters.JobManageAdapter
import com.lxc.recruitment.component.title
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentResumeManageBinding
import com.lxc.recruitment.entity.JobEntity
import com.lxc.recruitment.viewmodels.CompanyViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * 个人简历管理页面
 */
@AndroidEntryPoint
class JobsManageFragment: Fragment(){

    private val resumeList = mutableListOf<JobEntity>()

    private val adapter = JobManageAdapter(resumeList)

    private val viewModel: CompanyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentResumeManageBinding.inflate(inflater, container, false)
        this.title("简历管理")
        viewModel.jobList(SimpleRequestBean(Constants.USER.id))
        binding.resumeList.adapter = adapter

        viewModel.jobList.observe(viewLifecycleOwner, Observer {
            resumeList.clear()
            resumeList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        binding.btnAddNews.setOnClickListener {
            findNavController().navigate(R.id.action_jobsManageFragment_to_jobsInfoFragment)
        }
        return binding.root
    }

}