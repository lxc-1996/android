package com.lxc.base.http

import com.lxc.base.bean.ResponseBody
import com.lxc.base.constant.ResponseCode
import com.lxc.base.http.gson.GsonUtils
import com.lxc.base.util.LogUtils
import com.lxc.base.util.toastK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


/**
 * 网络请求工具类
 */
object HttpUtil {

//    private var mRetrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(AppConstants.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .callbackExecutor(Executors.newSingleThreadExecutor())
//        .build()
//
//    fun getUser(): UserService2 {
//        return mRetrofit.create(UserService2::class.java)
//    }
//}
///**
// * 获取用户信息
// */
//fun getUserInfo() {
//    val entity = UserEntity()
//    val data = HttpUtil.getUser().getUserInfo(entity)
//    data.postHttpObject(object : onHttpSuccess<UserEntity> {
//        override fun onSuccess(data: UserEntity) {
//        }
//    })
}

/**
 * 请求 Object 类型数据
 */
inline fun <reified T> Call<Any>.postHttpObject(callBack: onHttpSuccess<T>) {
    realPost<T>{ body, clazz ->
        val response = GsonUtils.fromJsonObject(body, clazz)
        callBack.onSuccess(response.data)
    }
}

/**
 * 请求 List 类型数据
 */
inline fun <reified T> Call<Any>.postHttpList(callBack: onHttpSuccess<List<T>>) {
    realPost<T>{ body, clazz ->
        val response = GsonUtils.fromJsonArray(body, clazz)
        callBack.onSuccess(response.data)
    }
}

inline fun <reified T> Call<Any>.realPost(crossinline parsing:(body: String, clazz: Class<T>)->Unit){
    val clazz = T::class.java
    this.enqueue(object : Callback<Any> {
        override fun onFailure(call: Call<Any>, t: Throwable) {
            println(t.toString())
        }

        override fun onResponse(
            call: Call<Any>,
            response: Response<Any>
        ) {
            try {
                val body = response.body().toString()
                LogUtils.json(body)
                val responseBody = GsonUtils.fromJson(body, ResponseBody::class.java)
                if(responseBody.code == ResponseCode.SUCCESS){
                    parsing(body,clazz)
                } else {
                    toastK(responseBody.title)
                }
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    })
}


/**
 * 网络回调成功
 */
interface onHttpSuccess<T> {

    fun onSuccess(data: T)
}
