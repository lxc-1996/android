package com.lxc.base.util

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * 日志工具类
 * 
 * @author lxc
 */
object LogUtils {

    private const val tag: String = "logcat"
    private var debuger: Boolean = false

    /**
     * 通过定义全局 log 日志，控制全局日志的打印，封装一层的好处是，
     * 全局替换实现的时，迁移代价小
     *
     * @param debug 是否打印日志
     */
    var isDebug: Boolean
        // 判断是否为 debug
        get() = debuger
        set(debug) {
            debuger = debug
            if (debug) {
                val formatStrategy = PrettyFormatStrategy.newBuilder()
                    // 线程信息
                    .showThreadInfo(true)
                    // 方法行数
                    .methodCount(0)
                    // 方法偏移量
                    .methodOffset(7)
                    .tag(tag)
                    .build()
                Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
            }
        }


    fun v(message: String, tag: String = this.tag) {
        if (debuger) {
            Logger.t(tag).v(message)
        }
    }

    fun d(message: String, tag: String = this.tag) {
        if (debuger) {
            Logger.t(tag).d(message)
        }
    }

    fun i(message: String, tag: String = this.tag) {
        if (debuger) {
            Logger.t(tag).i(message)
        }
    }

    fun w(message: String, tag: String = this.tag) {
        if (debuger) {
            Logger.t(tag).w(message)
        }
    }

    fun e(message: String, tag: String = this.tag) {
        if (debuger) {
            Logger.t(tag).e(message)
        }
    }

    /**
     * 打印 json 格式，或字符串格式
     */
    fun json(message: String, tag: String = this.tag) {
        if (debuger) {
            Logger.t(tag).json(message)
        }
    }
}