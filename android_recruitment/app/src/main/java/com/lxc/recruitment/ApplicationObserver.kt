package com.lxc.recruitment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 轻易知道何时进入前后台
 * 针对整个程序的监听
 */
class ApplicationObserver : LifecycleObserver {

    /**
     * 仅一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

    /**
     * 前台调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {

    }

    /**
     * 前台调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {

    }

    /**
     * 后台调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {

    }

    /**
     * 后台调用（有一定的延后，横竖屏切换不调用）
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {

    }

    /**
     * 永不调用（有一定的延后，横竖屏切换不调用）
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {

    }

}