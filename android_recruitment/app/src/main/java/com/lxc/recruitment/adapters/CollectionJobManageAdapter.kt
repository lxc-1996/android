package com.lxc.recruitment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lxc.recruitment.R
import com.lxc.recruitment.component.company.JobsInfoFragmentArgs
import com.lxc.recruitment.component.personal.ResumeInfoFragmentArgs
import com.lxc.recruitment.databinding.ListItemSimpleBinding
import com.lxc.recruitment.entity.CollectionEntity


class CollectionJobManageAdapter(val list: List<CollectionEntity>) :
    RecyclerView.Adapter<CollectionJobManageAdapter.JobViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(
            ListItemSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val collectionEntity = list[position]
        holder.bind(collectionEntity)
    }

    class JobViewHolder(
        private val binding: ListItemSimpleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionEntity) {
            binding.listItem.setOnClickListener {
                val args = JobsInfoFragmentArgs.Builder()
                    .setId(item.id)
                    .setIsCollect(1)
                    .setIsEdit(false).build()
                    .toBundle()
                it.findNavController().navigate(R.id.action_adminJobManagerFragment_to_jobsInfoFragment, args)
            }
            binding.tvAuthor.visibility = View.GONE

            binding.apply {
                title = "职位名:${item.jobName}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
