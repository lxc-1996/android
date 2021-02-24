package com.lxc.recruitment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lxc.recruitment.R
import com.lxc.recruitment.component.admin.NewsInfoFragmentArgs
import com.lxc.recruitment.databinding.ListItemSimpleBinding
import com.lxc.recruitment.entity.NewsEntity


class NewsManageAdapter(val list: List<NewsEntity>) :
    RecyclerView.Adapter<NewsManageAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsEntity = list[position]
        holder.bind(newsEntity)
    }

    class NewsViewHolder(
        private val binding: ListItemSimpleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsEntity) {

            binding.listItem.setOnClickListener {
                val args = NewsInfoFragmentArgs.Builder()
                    .setId(item.id)
                    .setIsEdit(false).build()
                    .toBundle()
                it.findNavController().navigate(R.id.action_homeFragment_to_newsInfoFragment, args)
            }
            binding.tvAuthor.visibility = View.GONE

            binding.apply {
                title = "标题:${item.title}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
