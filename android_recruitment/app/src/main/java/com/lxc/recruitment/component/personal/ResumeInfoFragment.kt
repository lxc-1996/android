package com.lxc.recruitment.component.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.base.util.toastK
import com.lxc.recruitment.R
import com.lxc.recruitment.component.admin.NewsInfoFragmentArgs
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentResumeInfoBinding
import com.lxc.recruitment.entity.DeliveryEntity
import com.lxc.recruitment.entity.ResumeEntity
import com.lxc.recruitment.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint


/**
 * 个人简历管理页面
 */
@AndroidEntryPoint
class ResumeInfoFragment: Fragment(){

    private val args: ResumeInfoFragmentArgs by navArgs()

    private lateinit var binding: FragmentResumeInfoBinding

    private val viewModel: PersonalViewModel by viewModels()

    private val companyViewModel: CompanyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResumeInfoBinding.inflate(inflater, container, false)
        viewModel.personal = Constants.PERSONAL
        binding.resume = ResumeEntity()
        binding.isEdit = args.isEdit

        binding.ltYear.setOnClickListener {
            getYearList {
                binding.ltYear.text = it.value
            }
        }
        binding.ltDegree.setOnClickListener {
            getDegreeList {
                binding.ltDegree.text = it.value
            }
        }
        binding.ltTreatment.setOnClickListener {
            getSalaryList {
                binding.ltTreatment.text = it.value
            }
        }

        if(!args.isEdit){
            viewModel.queryResume(SimpleRequestBean(args.id))
            viewModel.resume.observe(viewLifecycleOwner, Observer {
                binding.resume = it
            })
            if(args.isWatch){
                binding.btnSave.text = "面试邀请"
                binding.btnSave.setOnClickListener {
                    val delivery = DeliveryEntity()
                    delivery.jobId = binding.resume!!.id
                    delivery.sender = Constants.USER.id
                    delivery.senderName = Constants.COMPANY.name
                    companyViewModel.interview(delivery)
                    findNavController().navigateUp()
                }
            } else {
                binding.btnSave.text = "删除"
                binding.btnSave.setOnClickListener {
                    viewModel.deleteResume(SimpleRequestBean(args.id))
                    findNavController().navigateUp()
                }
            }
        } else {
            binding.btnSave.text = "保存"
            binding.btnSave.setOnClickListener {
                val news = binding.resume!!
                news.personalId = Constants.USER.id
                viewModel.addResume(binding.resume!!)
                findNavController().navigateUp()
            }
        }

        return binding.root
    }
}