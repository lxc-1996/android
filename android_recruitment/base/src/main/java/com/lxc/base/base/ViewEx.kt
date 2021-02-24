package com.lxc.base.base

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxc.base.R

/**
 * visibility
 */

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

//=====================================RecyclerView==========================================

/**
 * Setup adapter
 */
private fun RecyclerView.setAdapter(
    adapter: RecyclerView.Adapter<*>,
    emptyLayoutResId: Int = 0,
    animator: Boolean = false,
    setAdapter: () -> Unit
) {

    //  Set up personalization adapter
    setAdapter()
    // use animation？
    val animat = this.itemAnimator as DefaultItemAnimator
    animat.supportsChangeAnimations = animator
    // Check adapter type，Empty data view
    if (emptyLayoutResId != 0) {
        if (adapter is BaseQuickAdapter<*, *>) {
            adapter.setEmptyView(emptyLayoutResId)
        } else {
            throw java.lang.Exception("Adapter must extends BaseQuickAdapter.")
        }
    }
}

/**
 * 设置线性布局
 */
@SuppressLint("WrongConstant")
fun RecyclerView.setAdapterL(
    adapter: RecyclerView.Adapter<*>,
    divider: Boolean = false,
    emptyLayoutResId: Int = R.layout.adapter_empty,
    orientation: Int = LinearLayoutManager.VERTICAL,
    animator: Boolean = false
) {

    setAdapter(adapter, emptyLayoutResId, animator) {
        this.layoutManager = LinearLayoutManager(context, orientation, false)
        this.adapter = adapter
        // use divider？
        if (divider) {
            if (orientation == LinearLayoutManager.VERTICAL) {
                this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            } else {
                this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
            }
        }
    }

}

/**
 * 设置网格布局
 */
fun RecyclerView.setAdapterG(
    adapter: RecyclerView.Adapter<*>,
    animator: Boolean = false,
    emptyLayoutResId: Int = R.layout.adapter_empty,
    orientation: Int=RecyclerView.VERTICAL,
    line: Int = 1
) {
    setAdapter(adapter, emptyLayoutResId, animator) {
        this.layoutManager = GridLayoutManager(context, line,orientation,false)
        this.adapter = adapter
    }
}

/**
 * 设置瀑布流
 */
fun RecyclerView.setAdapterS(
    adapter: RecyclerView.Adapter<*>,
    animator: Boolean = false,
    emptyLayoutResId: Int = R.layout.adapter_empty,
    line: Int = 1,
    orientation: Int = StaggeredGridLayoutManager.VERTICAL
) {
    setAdapter(adapter, emptyLayoutResId, animator) {
        this.layoutManager = StaggeredGridLayoutManager(line, orientation)
        this.adapter = adapter
    }
}

//======================================TextView=========================================

/**
 * 根据字符内容，控制是否可见
 */
fun TextView.visiblyOfStringNotEmpty(str: String, view: View = this) {
    if (str.isEmpty()) {
        view.gone()
    } else {
        view.visible()
    }
}

fun TextView.visiblyOfStringNotEmptyAndSetString(str: String, view: View = this) {
    if (str.isEmpty()) {
        view.gone()
    } else {
        view.visible()
        this.text = str
    }
}

/**
 * getText 是否为 empty 或 null
 */
fun TextView.isEmpty(): Boolean {
    return TextUtils.isEmpty(this.text)
}

/**
 * 获取去空文字
 */
fun TextView.getTextTrim(): String {
    return this.text.toString().trim { it <= ' ' }
}

//======================================View=========================================
/**
 * 根据字符内容，控制是否可见
 */
fun View.visiblyOfStringNotEmpty(str: String, view: View = this) {
    if (str.isEmpty()) {
        view.gone()
    } else {
        view.visible()
    }
}

/**
 *  抢占焦点
 */
fun View.grabFocus() {
    this.isFocusable = true
    this.isFocusableInTouchMode = true
    this.requestFocus()
}

