package com.lxc.recruitment.component.company

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
import com.lxc.recruitment.component.personal.ResumeInfoFragmentArgs
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentJobsInfoBinding
import com.lxc.recruitment.databinding.FragmentResumeInfoBinding
import com.lxc.recruitment.entity.CollectionEntity
import com.lxc.recruitment.entity.DeliveryEntity
import com.lxc.recruitment.entity.JobEntity
import com.lxc.recruitment.entity.ResumeEntity
import com.lxc.recruitment.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint


/**
 * 工作管理页面
 */
@AndroidEntryPoint
class JobsInfoFragment : Fragment() {

    private val args: JobsInfoFragmentArgs by navArgs()

    private lateinit var binding: FragmentJobsInfoBinding

    private val viewModel: CompanyViewModel by viewModels()

    private val personalViewModel: PersonalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobsInfoBinding.inflate(inflater, container, false)
        viewModel.company = Constants.COMPANY
        binding.job = JobEntity()
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


        if (!args.isEdit) {
            viewModel.jobQuery(SimpleRequestBean(args.id))
            viewModel.job.observe(viewLifecycleOwner, Observer {
                binding.job = it
            })
            when (args.isCollect) {
                0 -> {
                    binding.btnSave.text = "删除"
                    binding.btnSave.setOnClickListener {
                        viewModel.jobDelete(SimpleRequestBean(args.id))
                        findNavController().navigateUp()
                    }
                }
                1 -> {
                    binding.btnSave.text = "取消收藏"
                    binding.btnSave.setOnClickListener {
                        personalViewModel.cancelCollectionJob(SimpleRequestBean(args.id))
                        findNavController().navigateUp()
                    }
                }
                2 -> {
                    binding.btnSave.text = "收藏"
                    binding.btnSave.setOnClickListener {
                        val collection = CollectionEntity()
                        collection.jobName = binding.job!!.name
                        collection.jobId = binding.job!!.id
                        collection.userId = Constants.USER.id
                        personalViewModel.collectionJob(collection)
                        findNavController().navigateUp()
                    }
                    // 投递简历
                    binding.btnDeliveryResume.visibility = View.VISIBLE
                    binding.btnDeliveryResume.setOnClickListener {
                        val delivery = DeliveryEntity()
                        delivery.jobId = binding.job!!.id
                        delivery.sender = Constants.USER.id
                        delivery.senderName = Constants.PERSONAL.name
                        personalViewModel.deliveryResume(delivery)
                        findNavController().navigateUp()
                    }
                }
            }

        } else {
            binding.btnSave.text = "保存"
            binding.btnSave.setOnClickListener {
                val job = binding.job!!
                job.companyId = Constants.USER.id
                viewModel.jobAdd(binding.job!!)
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}