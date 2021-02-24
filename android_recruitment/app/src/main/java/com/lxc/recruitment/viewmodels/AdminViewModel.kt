package com.lxc.recruitment.viewmodels

import androidx.lifecycle.MutableLiveData
import com.lxc.base.base.BaseActionEvent
import com.lxc.base.base.BaseViewModel
import com.lxc.base.base.HttpObserver
import com.lxc.base.base.RxJavaUtils
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.base.http.entity.UserEntity
import com.lxc.base.util.toastK
import com.lxc.recruitment.api.AdminService
import com.lxc.recruitment.api.HttpService
import com.lxc.recruitment.api.PersonalService
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.entity.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val service: AdminService
) : BaseViewModel() {


    var admin:AdministratorEntity = AdministratorEntity()

    /**
     * 查询管理员个人信息
     */
    fun queryAdminInfo(){
        service.queryAdmin(SimpleRequestBean(Constants.USER.id))
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<AdministratorEntity>() {
                override fun onSuccess(t: AdministratorEntity) {
                    admin = t
                }
            })
    }

    var userList: MutableLiveData<List<UserEntity>> = MutableLiveData()
    fun userList(){
        service.userList(SimpleRequestBean(Constants.USER.id))
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object :HttpObserver<List<UserEntity>>(){
                override fun onSuccess(t: List<UserEntity>) {

                }
            })
    }

    fun newsAdd(body: NewsEntity){
        service.newsAdd(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object :HttpObserver<NewsEntity>(){
                override fun onSuccess(t: NewsEntity) {

                }
            })
    }

    fun newsDelete(body: SimpleRequestBean){
        service.newsDelete(body)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object :HttpObserver<String>(){
                override fun onSuccess(t: String) {

                }
            })
    }

    fun addAdminInfo() {
        admin.id = Constants.USER.id
        service.addAdmin(admin)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object :HttpObserver<AdministratorEntity>(){
                override fun onSuccess(t: AdministratorEntity) {

                }
            })
    }

    /**
     * 待审核职位
     */
    var auditList: MutableLiveData<List<JobEntity>> = MutableLiveData()
    fun auditList() {
        HttpService.getCompany().jobList(SimpleRequestBean(-1))
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<JobEntity>>() {
                override fun onSuccess(t: List<JobEntity>) {
                    auditList.value = t
                }
            })
    }


    /**
     * 获取用户接收到的信息
     */
    var newsList: MutableLiveData<List<NewsEntity>> = MutableLiveData()
    fun getNews() {
        service.newsList(SimpleRequestBean(Constants.USER.id))
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<NewsEntity>>() {
                override fun onSuccess(t: List<NewsEntity>) {
                    newsList.value = t
                }
            })
    }

    var new: MutableLiveData<NewsEntity> = MutableLiveData()
    fun newQuery(id:Long){
        service.newsQuery(SimpleRequestBean(id))
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<NewsEntity>() {
                override fun onSuccess(t: NewsEntity) {
                    new.value = t
                }
            })
    }
}

