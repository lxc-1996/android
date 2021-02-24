package com.lxc.recruitment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lxc.recruitment.R
import com.lxc.recruitment.component.personal.ResumeInfoFragmentArgs
import com.lxc.recruitment.databinding.ListItemSimpleBinding
import com.lxc.recruitment.entity.ResumeEntity


class ResumeManageAdapter(val list: List<ResumeEntity>) :
    RecyclerView.Adapter<ResumeManageAdapter.ResumeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeViewHolder {
        return ResumeViewHolder(
            ListItemSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResumeViewHolder, position: Int) {
        val resumeEntity = list[position]
        holder.bind(resumeEntity)
    }

    class ResumeViewHolder(
        private val binding: ListItemSimpleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResumeEntity) {

            binding.listItem.setOnClickListener {
                val args = ResumeInfoFragmentArgs.Builder()
                    .setId(item.id)
                    .setIsEdit(false).build()
                    .toBundle()
                it.findNavController().navigate(R.id.action_resumeManageFragment_to_resumeInfoFragment, args)
            }
            binding.tvAuthor.visibility = View.GONE

            binding.apply {
                title = "简历名:${item.name}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
