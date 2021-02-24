package com.lxc.recruitment

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.lxc.base.util.JLibrary
import dagger.hilt.android.HiltAndroidApp

/**
 * @author lxc
 */
@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        JLibrary.init(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }
}