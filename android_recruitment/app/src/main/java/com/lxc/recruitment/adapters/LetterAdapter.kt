package com.lxc.recruitment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lxc.recruitment.component.home.MainFragmentDirections
import com.lxc.recruitment.databinding.ListItemSimpleBinding
import com.lxc.recruitment.entity.LetterEntity


class LetterAdapter(val list: List<LetterEntity>) :
    RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        return LetterViewHolder(
            ListItemSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val letterEntity = list[position]
        holder.bind(letterEntity)
    }

    class LetterViewHolder(
        private val binding: ListItemSimpleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.listItem.setOnClickListener {
                val direction = MainFragmentDirections.actionHomeFragmentToLetterInfoFragment().setIsEdit(false)
                it.findNavController().navigate(direction)
            }
        }

        fun bind(item: LetterEntity) {
            binding.apply {
                author = "发件人:${item.senderName}"
                title = "标题:${item.title}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
