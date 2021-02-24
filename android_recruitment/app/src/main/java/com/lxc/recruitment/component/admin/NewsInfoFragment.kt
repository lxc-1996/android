
package com.lxc.recruitment.component.admin

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
import com.lxc.base.http.entity.UserEntity
import com.lxc.base.util.toastK
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.databinding.FragmentNewsInfoBinding
import com.lxc.recruitment.databinding.FragmentResumeInfoBinding
import com.lxc.recruitment.entity.NewsEntity
import com.lxc.recruitment.entity.ResumeEntity
import com.lxc.recruitment.viewmodels.AdminViewModel
import com.lxc.recruitment.viewmodels.PersonalViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * 工作管理页面
 */
@AndroidEntryPoint
class NewsInfoFragment: Fragment(){

    private val args: NewsInfoFragmentArgs by navArgs()

    private lateinit var binding: FragmentNewsInfoBinding

    private val viewModel: AdminViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsInfoBinding.inflate(inflater, container, false)
        binding.news = NewsEntity()
        binding.isEdit = args.isEdit
        if(!args.isEdit){
            viewModel.newQuery(args.id)
            viewModel.new.observe(viewLifecycleOwner, Observer {
                binding.news = it
            })
            if(Constants.USER.type == UserEntity.ADMIN){
                binding.btnSave.text = "删除"
                binding.btnSave.setOnClickListener {
                    viewModel.newsDelete(SimpleRequestBean(args.id))
                    findNavController().navigateUp()
                }
            }
        } else {
            binding.btnSave.text = "保存"
            binding.btnSave.setOnClickListener {
                val news = binding.news!!
                news.adminId = Constants.USER.id
                viewModel.newsAdd(news)
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}