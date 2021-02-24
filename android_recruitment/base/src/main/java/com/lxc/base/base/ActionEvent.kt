package com.lxc.base.base


open class BaseEvent(val action: Int)

class BaseActionEvent(action: Int) : BaseEvent(action) {

    companion object {
        const val SHOW_LOADING_DIALOG = 1
        const val DISMISS_LOADING_DIALOG = 2
        const val SHOW_TOAST = 3
        const val FINISH = 4
        const val FINISH_WITH_RESULT_OK = 5
    }
}