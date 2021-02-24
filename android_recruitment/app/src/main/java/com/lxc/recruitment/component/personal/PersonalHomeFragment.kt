package com.lxc.recruitment.component.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.recruitment.R
import com.lxc.recruitment.adapters.JobManageAdapter
import com.lxc.recruitment.component.admin.NewsInfoFragmentArgs
import com.lxc.recruitment.component.title
import com.lxc.recruitment.databinding.FragmentPersonalHomeBinding
import com.lxc.recruitment.entity.JobEntity
import com.lxc.recruitment.entity.ResumeEntity
import com.lxc.recruitment.viewmodels.*


class PersonalHomeFragment : Fragment() {

    private lateinit var binding: FragmentPersonalHomeBinding

    private val viewModel: AdminViewModel by activityViewModels()

    private val userViewModel: UserViewModel by activityViewModels()

    private val personalViewModel: PersonalViewModel by activityViewModels()

    private val companyViewModel : CompanyViewModel by activityViewModels()

    private val jobList = mutableListOf<JobEntity>()

    private val adapter = JobManageAdapter(jobList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.title("首页")
        binding = FragmentPersonalHomeBinding.inflate(inflater, container, false)

        viewModel.getNews()
        viewModel.newsList.observe(viewLifecycleOwner, Observer { list ->
            val autoRes = mutableListOf<String>()
            list.forEach {
                autoRes.add(it.title)
            }
            binding.switcher.setResources(autoRes.toTypedArray())
            binding.switcher.setTextStillTime(3000)
            binding.switcher.setOnClickListener {
                val text = binding.switcher.currentText
                val index = autoRes.indexOf(text)
                index.let {
                    val args =
                        NewsInfoFragmentArgs.Builder().setId(list[it].id).setIsEdit(false).build()
                            .toBundle()
                    findNavController().navigate(R.id.action_homeFragment_to_newsInfoFragment, args)
                }
            }
        })

        binding.btnYear.setOnClickListener {
            getYearList {
                binding.btnYear.text = it.value
            }
        }
        binding.btnDegree.setOnClickListener {
            getDegreeList {
                binding.btnDegree.text = it.value
            }
        }
        binding.btnSalary.setOnClickListener {
            getSalaryList {
                binding.btnSalary.text = it.value
            }
        }


        companyViewModel.jobList(SimpleRequestBean(0))
        binding.jobList.adapter = adapter
        companyViewModel.jobList.observe(viewLifecycleOwner, Observer {
            jobList.clear()
            jobList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        binding.btnSearch.setOnClickListener {
            val resumeEntity = JobEntity()
            resumeEntity.duration = binding.btnYear.text.toString()
            resumeEntity.degree = binding.btnDegree.text.toString()
            resumeEntity.treatment = binding.btnSalary.text.toString()
            personalViewModel.searchJob(resumeEntity)
        }

        personalViewModel.jobList.observe(viewLifecycleOwner, Observer {
            jobList.clear()
            jobList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }
}