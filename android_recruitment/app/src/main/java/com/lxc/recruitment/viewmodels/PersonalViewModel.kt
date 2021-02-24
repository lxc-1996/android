package com.lxc.recruitment.viewmodels

import androidx.lifecycle.MutableLiveData
import com.lxc.base.base.BaseActionEvent
import com.lxc.base.base.BaseViewModel
import com.lxc.base.base.HttpObserver
import com.lxc.base.base.RxJavaUtils
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.base.util.toastK
import com.lxc.recruitment.api.PersonalService
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.entity.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalViewModel @Inject constructor(
    private val service: PersonalService
) : BaseViewModel() {

    var personal:PersonalEntity = PersonalEntity()

    var pos = 0

    companion object {
        const val VIEW_MODEL_RESUME_ADD_SUCCESS = 5
    }

    /**
     * 获取个人信息
     */
    fun queryPersonalInfo(){
        val simpleRequestBean =SimpleRequestBean(Constants.USER.id)
        service.queryPersonalInfo(simpleRequestBean)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<PersonalEntity>(){
                override fun onSuccess(t: PersonalEntity) {
                    Constants.PERSONAL = t
                }
            })
    }

    /**
     * 修改个人信息
     */
    fun addPersonalInfo() {
        personal.userId = Constants.USER.id
        service.addPersonalInfo(personal)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object :HttpObserver<PersonalEntity>(){
                override fun onSuccess(t: PersonalEntity) {
                    Constants.PERSONAL = t
                }
            })
    }

    /**
     * 添加简历
     */
    fun addResume(resume: ResumeEntity){
        resume.personalId = Constants.USER.id
        if(resume.name.isNullOrEmpty()){
            toastK("简历名不能为空")
            return
        }
        service.addResume(resume)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object :HttpObserver<ResumeEntity>(){
                override fun onSuccess(t: ResumeEntity) {
                    actionLiveData.value = BaseActionEvent(VIEW_MODEL_RESUME_ADD_SUCCESS)
                }
            })
    }

    /**
     * 删除简历
     */
    fun deleteResume(body: SimpleRequestBean){
        service.resumeDelete(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<String>() {
                override fun onSuccess(t: String) {

                }
            })
    }

    val resumeList = MutableLiveData<List<ResumeEntity>>()
    fun resumeList(body: SimpleRequestBean){
        service.resumeList(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<ResumeEntity>>() {
                override fun onSuccess(t: List<ResumeEntity>) {
                    resumeList.value = t
                }
            })
    }

    var resume: MutableLiveData<ResumeEntity> = MutableLiveData()
    fun queryResume(body: SimpleRequestBean){
        service.queryResume(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<ResumeEntity>() {
                override fun onSuccess(t: ResumeEntity) {
                    resume.value = t
                }
            })
    }

    /**
     * 收藏职位
     */
    var collection: MutableLiveData<CollectionEntity> = MutableLiveData()
    fun collectionJob(body: CollectionEntity){
        service.collectionJob(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<CollectionEntity>() {
                override fun onSuccess(t: CollectionEntity) {
                    collection.value = t
                }
            })
    }

    /**
     * 收藏列表
     */
    val collectionList = MutableLiveData<List<CollectionEntity>>()
    fun collectionList(body: SimpleRequestBean){
        service.collectionList(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<CollectionEntity>>() {
                override fun onSuccess(t: List<CollectionEntity>) {

                }
            })
    }

    /**
     * 取消收藏
     */
    fun cancelCollectionJob(body: SimpleRequestBean){
        service.cancelCollectionJob(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<String>() {
                override fun onSuccess(t: String) {

                }
            })
    }

    fun deliveryResume(body: DeliveryEntity){
        service.deliveryResume(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<DeliveryEntity>() {
                override fun onSuccess(t: DeliveryEntity) {

                }
            })
    }

    /**
     * 搜索工作
     */
    val jobList = MutableLiveData<List<JobEntity>>()
    fun searchJob(body: JobEntity){
        service.searchJob(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<JobEntity>>() {
                override fun onSuccess(t: List<JobEntity>) {
                        jobList.value = t
                }
            })
    }
}

