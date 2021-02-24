package com.lxc.recruitment.viewmodels

import androidx.lifecycle.MutableLiveData
import com.lxc.base.base.BaseActionEvent
import com.lxc.base.base.BaseViewModel
import com.lxc.base.base.HttpObserver
import com.lxc.base.base.RxJavaUtils
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.recruitment.api.CompanyService
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.entity.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val service: CompanyService
) : BaseViewModel() {

    var company:CompanyEntity = CompanyEntity()

    companion object {
        const val VIEW_MODEL_JOB_ADD_SUCCESS = 5
    }

    fun queryCompanyInfo() {
        service.queryCompanyInfo(SimpleRequestBean(Constants.USER.id))
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<CompanyEntity>() {
                override fun onSuccess(t: CompanyEntity) {
                    Constants.COMPANY = t
                }
            })
    }

    fun addCompanyInfo() {
        service.addCompanyInfo(company)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<CompanyEntity>() {
                override fun onSuccess(t: CompanyEntity) {
                    Constants.COMPANY = t
                }
            })
    }

    fun jobAdd(body: JobEntity) {
        service.jobAdd(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<JobEntity>() {
                override fun onSuccess(t: JobEntity) {
                    actionLiveData.value = BaseActionEvent(VIEW_MODEL_JOB_ADD_SUCCESS)
                }
            })
    }


    fun jobDelete(body: SimpleRequestBean) {
        service.jobDelete(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<String>() {
                override fun onSuccess(t: String) {
                }
            })
    }

    val jobList = MutableLiveData<List<JobEntity>>()
    fun jobList(body: SimpleRequestBean) {
        service.jobList(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<JobEntity>>() {
                override fun onSuccess(t: List<JobEntity>) {
                    jobList.value = t
                }
            })
    }

    val job = MutableLiveData<JobEntity>()
    fun jobQuery(body: SimpleRequestBean) {
        service.jobQuery(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<JobEntity>() {
                override fun onSuccess(t: JobEntity) {
                    job.value = t
                }
            })
    }

    fun interview(body: DeliveryEntity) {
        service.interview(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<DeliveryEntity>() {
                override fun onSuccess(t: DeliveryEntity) {
                }
            })
    }

    val resumeList = MutableLiveData<List<ResumeEntity>>()
    fun resumeSearch(body: ResumeEntity) {
        service.resumeSearch(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<ResumeEntity>>() {
                override fun onSuccess(t: List<ResumeEntity>) {
                    resumeList.value = t
                }
            })
    }
}

