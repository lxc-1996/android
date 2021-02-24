package com.lxc.recruitment.api

import com.lxc.base.http.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 网络请求服务
 */
object HttpService {

    private val mOkHttpClient = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    private var mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .client(mOkHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUser(): UserService {
        return mRetrofit.create(UserService::class.java)
    }

    fun getPersonal():PersonalService{
        return mRetrofit.create(PersonalService::class.java)
    }

    fun getAdmin():AdminService{
        return mRetrofit.create(AdminService::class.java)
    }

    fun getCompany():CompanyService{
        return mRetrofit.create(CompanyService::class.java)
    }
}