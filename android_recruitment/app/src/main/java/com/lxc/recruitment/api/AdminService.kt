package com.lxc.recruitment.api

import com.lxc.base.bean.ResponseBody
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.base.constant.UrlConstant
import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.entity.*
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST


interface AdminService {

    @POST("${UrlConstant.ADMIN}${UrlConstant.USER_DELETE}")
    fun userDelete(@Body body: UserDeleteEntity):Observable<ResponseBody<UserDeleteEntity>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.AUDIT_JOB}")
    fun auditJob(@Body body:AuditEntity): Observable<ResponseBody<AuditEntity>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.USER_LIST}")
    fun userList(@Body body:SimpleRequestBean):Observable<ResponseBody<List<UserEntity>>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.NEWS_ADD}")
    fun newsAdd(@Body body:NewsEntity):Observable<ResponseBody<NewsEntity>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.NEWS_DELETE}")
    fun newsDelete(@Body body:SimpleRequestBean):Observable<ResponseBody<String>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.ADD_ADMIN}")
    fun addAdmin(@Body body:AdministratorEntity):Observable<ResponseBody<AdministratorEntity>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.NEWS_LIST}")
    fun newsList(@Body body: SimpleRequestBean):Observable<ResponseBody<List<NewsEntity>>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.NEWS_QUERY}")
    fun newsQuery(@Body body: SimpleRequestBean):Observable<ResponseBody<NewsEntity>>

    @POST("${UrlConstant.ADMIN}${UrlConstant.ADMIN_QUERY}")
    fun queryAdmin(@Body body:SimpleRequestBean):Observable<ResponseBody<AdministratorEntity>>
}