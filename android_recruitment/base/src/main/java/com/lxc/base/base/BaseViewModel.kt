package com.lxc.base.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * @Author: leavesC
 * @Date: 2020/7/24 0:43
 * @Desc: BaseViewModel
 * @GitHub：https://github.com/leavesC
 */
open class BaseViewModel : ViewModel() {

    var actionLiveData: MutableLiveData<BaseActionEvent> = MutableLiveData()

}
