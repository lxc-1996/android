package com.lxc.recruitment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.api.HttpService
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.ListItemSimpleTwoButtomBinding
import com.lxc.recruitment.entity.UserDeleteEntity


class UserManageAdapter(val list: List<UserEntity>) :
    RecyclerView.Adapter<UserManageAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ListItemSimpleTwoButtomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userEntity = list[position]
        holder.bind(userEntity)
    }

    class UserViewHolder(
        private val binding: ListItemSimpleTwoButtomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserEntity) {
            binding.btnDelete.setOnClickListener {
                val userDeleteEntity = UserDeleteEntity()
                userDeleteEntity.deletedId = item.id
                userDeleteEntity.adminId = Constants.USER.id
                HttpService.getAdmin().userDelete(userDeleteEntity)
            }

            binding.apply {
                title = "标题:${item.account}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
