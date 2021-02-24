package com.lxc.base.util

import android.annotation.SuppressLint
import android.content.Context

/**
 * 全局Context初始化
 */


object JLibrary {
    @SuppressLint("StaticFieldLeak")
    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
        LogUtils.isDebug = true
    }

    fun context(): Context {
        if (context == null) {
            throw NullPointerException("You must first initialize it with init.")
        }
        return context!!
    }
}

