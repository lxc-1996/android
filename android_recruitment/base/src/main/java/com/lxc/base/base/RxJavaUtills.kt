package com.lxc.base.base

import com.lxc.base.base.exception.ParamterInvalidException
import com.lxc.base.bean.ResponseBody
import com.lxc.base.constant.ResponseCode
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers


object RxJavaUtils {

    @JvmStatic
    fun <T> applySchedulers():ObservableTransformer<ResponseBody<T>, T>{
        return  ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    when(it.code) {
                        ResponseCode.SUCCESS -> {
                            return@flatMap createData(it.data)
                        }
                        else -> {
                            throw ParamterInvalidException(it.code,it.title)
                        }
                    }
                }
        }
    }

    private fun <T> createData(t: T): Observable<T>? {
        return Observable.create { emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}