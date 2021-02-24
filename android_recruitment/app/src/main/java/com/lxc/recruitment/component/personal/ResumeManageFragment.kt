package com.lxc.recruitment.component.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.lxc.recruitment.R
import com.lxc.base.bean.BaseFragment
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.recruitment.adapters.ResumeManageAdapter
import com.lxc.recruitment.component.title
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentResumeManageBinding
import com.lxc.recruitment.entity.NewsEntity
import com.lxc.recruitment.entity.ResumeEntity
import com.lxc.recruitment.viewmodels.AdminViewModel
import com.lxc.recruitment.viewmodels.PersonalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * 个人简历管理页面
 */
@AndroidEntryPoint
class ResumeManageFragment: Fragment(){

    private val resumeList = mutableListOf<ResumeEntity>()

    private val adapter = ResumeManageAdapter(resumeList)

    private val viewModel: PersonalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentResumeManageBinding.inflate(inflater, container, false)
        this.title("简历管理")
        viewModel.resumeList(SimpleRequestBean(Constants.USER.id))
        binding.resumeList.adapter = adapter

        viewModel.resumeList.observe(viewLifecycleOwner, Observer {
            resumeList.clear()
            resumeList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        binding.btnAddNews.setOnClickListener {
            findNavController().navigate(R.id.action_resumeManageFragment_to_resumeInfoFragment)
        }
        return binding.root
    }
}