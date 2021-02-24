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


interface CompanyService {

    @POST("${UrlConstant.COMPANY}${UrlConstant.COMPANY_INFO_ADD}")
    fun addCompanyInfo(@Body body: CompanyEntity):Observable<ResponseBody<CompanyEntity>>

    @POST("${UrlConstant.COMPANY}${UrlConstant.COMPANY_INFO_QUERY}")
    fun queryCompanyInfo(@Body body:SimpleRequestBean): Observable<ResponseBody<CompanyEntity>>

    @POST("${UrlConstant.COMPANY}${UrlConstant.RESUME_SEARCH}")
    fun resumeSearch(@Body body: ResumeEntity):Observable<ResponseBody<List<ResumeEntity>>>

    @POST("${UrlConstant.COMPANY}${UrlConstant.JOB_ADD}")
    fun jobAdd(@Body body:JobEntity):Observable<ResponseBody<JobEntity>>

    @POST("${UrlConstant.COMPANY}${UrlConstant.JOB_DELETE}")
    fun jobDelete(@Body body:SimpleRequestBean):Observable<ResponseBody<String>>

    // 0 和 非0
    @POST("${UrlConstant.COMPANY}${UrlConstant.JOB_LIST}")
    fun jobList(@Body body:SimpleRequestBean):Observable<ResponseBody<List<JobEntity>>>

    @POST("${UrlConstant.COMPANY}${UrlConstant.JOB_QUERY}")
    fun jobQuery(@Body body:SimpleRequestBean):Observable<ResponseBody<JobEntity>>

    @POST("${UrlConstant.COMPANY}${UrlConstant.INTERVIEW}")
    fun interview(@Body body: DeliveryEntity):Observable<ResponseBody<DeliveryEntity>>
}