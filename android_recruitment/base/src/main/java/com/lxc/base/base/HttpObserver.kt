package com.lxc.base.base

import com.lxc.base.util.LogUtils
import com.lxc.base.util.toastK
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

abstract class HttpObserver<T> : Observer<T> {
    override fun onSubscribe(d: @NonNull Disposable?) {}
    override fun onError(e: @NonNull Throwable?) {
        e?.let {
            it.message?.let {str ->
                toastK(str)
                LogUtils.e(str)
            }
        }
    }
    override fun onComplete() {}

    override fun onNext(t: T) {
        onSuccess(t)
    }

    abstract fun onSuccess(t:T)
}