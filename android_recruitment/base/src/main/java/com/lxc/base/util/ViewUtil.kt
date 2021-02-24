package com.lxc.base.util

import android.content.Context
import android.widget.Toast

fun toastK(content:String){
    Toast.makeText(mContext(), content, Toast.LENGTH_SHORT).show()
}

fun mContext(): Context {
    return JLibrary.context()
}