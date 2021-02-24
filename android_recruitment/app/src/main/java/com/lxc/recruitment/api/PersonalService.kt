package com.lxc.recruitment.api

import com.lxc.base.bean.ResponseBody
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.base.constant.UrlConstant
import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.entity.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 个人服务
 */
interface PersonalService {

    @POST("${UrlConstant.PERSONAL}${UrlConstant.SEARCH_JOB}")
    fun searchJob(@Body body: JobEntity): Observable<ResponseBody<List<JobEntity>>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.ADD_PERSONAL_INFO}")
    fun addPersonalInfo(@Body body: PersonalEntity): Observable<ResponseBody<PersonalEntity>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.QUERY_PERSONAL_INFO}")
    fun queryPersonalInfo(@Body body: SimpleRequestBean): Observable<ResponseBody<PersonalEntity>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.COLLECTION_JOB}")
    fun collectionJob(@Body body: CollectionEntity): Observable<ResponseBody<CollectionEntity>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.CANCEL_COLLECTION_JOB}")
    fun cancelCollectionJob(@Body body: SimpleRequestBean): Observable<ResponseBody<String>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.RESUME_DELETE}")
    fun resumeDelete(@Body body: SimpleRequestBean): Observable<ResponseBody<String>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.RESUME_ADD}")
    fun addResume(@Body body: ResumeEntity): Observable<ResponseBody<ResumeEntity>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.RESUME_LIST}")
    fun resumeList(@Body body: SimpleRequestBean): Observable<ResponseBody<List<ResumeEntity>>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.RESUME_QUERY}")
    fun queryResume(@Body body: SimpleRequestBean): Observable<ResponseBody<ResumeEntity>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.RESUME_DELIVERY}")
    fun deliveryResume(@Body body: DeliveryEntity): Observable<ResponseBody<DeliveryEntity>>

    @POST("${UrlConstant.PERSONAL}${UrlConstant.COLLECTION_LIST}")
    fun collectionList(@Body simpleRequestBean: SimpleRequestBean): Observable<ResponseBody<List<CollectionEntity>>>
}