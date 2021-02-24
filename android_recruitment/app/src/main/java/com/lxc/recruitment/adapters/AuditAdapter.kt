package com.lxc.recruitment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lxc.recruitment.R
import com.lxc.recruitment.api.HttpService
import com.lxc.recruitment.component.admin.NewsInfoFragmentArgs
import com.lxc.recruitment.component.company.JobsInfoFragmentArgs
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.ListItemSimpleTwoButtomBinding
import com.lxc.recruitment.entity.AuditEntity
import com.lxc.recruitment.entity.JobEntity


class AuditAdapter(val list: List<JobEntity>) :
    RecyclerView.Adapter<AuditAdapter.JobViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(
            ListItemSimpleTwoButtomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val jobEntity = list[position]
        holder.bind(jobEntity)
    }

    class JobViewHolder(
        private val binding: ListItemSimpleTwoButtomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: JobEntity) {
            binding.btnDelete.setOnClickListener {
                binding.btnDelete.text = "通过"
                val audit = AuditEntity()
                audit.adminId = Constants.USER.id
                audit.jobId = item.id
                HttpService.getAdmin().auditJob(audit)
            }
            binding.btnWatch.setOnClickListener {
                binding.listItem.setOnClickListener {
                    val args = JobsInfoFragmentArgs.Builder()
                        .setId(item.id)
                        .setIsEdit(false).build()
                        .toBundle()
                    it.findNavController()
                        .navigate(R.id.action_adminJobManagerFragment_to_jobsInfoFragment,args)
                }
            }

            binding.apply {
                title = "标题:${item.name}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
