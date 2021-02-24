package com.lxc.recruitment.api

import com.lxc.base.bean.ResponseBody
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.base.constant.UrlConstant
import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.entity.DictEntity
import com.lxc.recruitment.entity.LetterEntity
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST


interface UserService {

    @POST("${UrlConstant.USER}${UrlConstant.REGISTER}")
    fun register(@Body body: UserEntity):Observable<ResponseBody<UserEntity>>

    @POST("${UrlConstant.USER}${UrlConstant.LOGIN}")
    fun login(@Body body:UserEntity): Observable<ResponseBody<UserEntity>>

    @POST("${UrlConstant.USER}${UrlConstant.CHANGE_PASSWORD}")
    fun findPassword(@Body body:UserEntity):Observable<ResponseBody<UserEntity>>

    @POST("${UrlConstant.USER}${UrlConstant.USER_INFO}")
    fun getUserInfo(@Body body:UserEntity):Observable<ResponseBody<UserEntity>>

    @POST("${UrlConstant.USER}${UrlConstant.USER_RECEIVER}")
    fun getReceiver(@Body body:SimpleRequestBean):Observable<ResponseBody<List<LetterEntity>>>

    @POST("${UrlConstant.USER}${UrlConstant.LETTER}")
    fun sendLetter(@Body body:LetterEntity):Observable<ResponseBody<LetterEntity>>

    @POST("${UrlConstant.USER}${UrlConstant.DICT}")
    fun dict():Observable<ResponseBody<List<DictEntity>>>
}