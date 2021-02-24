package com.lxc.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment : Fragment(){

    private val baseViewModel:BaseViewModel by lazy {
        setBaseViewModel()
    }

    open fun setBaseViewModel(): BaseViewModel{
        return BaseViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseViewModel.actionLiveData.observe(viewLifecycleOwner, Observer {
            observer(it.action)
        })
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    open fun observer(action: Int){

    }

}