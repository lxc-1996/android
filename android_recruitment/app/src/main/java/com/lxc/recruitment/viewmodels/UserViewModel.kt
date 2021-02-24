package com.lxc.recruitment.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.lxc.base.base.BaseActionEvent
import com.lxc.base.base.BaseViewModel
import com.lxc.base.base.HttpObserver
import com.lxc.base.base.RxJavaUtils
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.base.http.entity.UserEntity
import com.lxc.base.util.toastK
import com.lxc.recruitment.api.UserService
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.entity.DictEntity
import com.lxc.recruitment.entity.LetterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val service: UserService
) : BaseViewModel() {


    // 共享 userBean
    var userBean: UserEntity = UserEntity()

    companion object {

        const val VIEW_MODEL_LOGIN_SUCCESS = 5

        const val VIEW_MODEL_REGISTER_SUCCESS = 6

        const val VIEW_MODEL_CHANGE_PASSWORD_SUCCESS = 7

        const val VIEW_MODEL_CHANGE_USER_INFO_SUCCESS = 8
    }

    init {
        userBean.account = "admin"
        userBean.password = "123456"
        userBean.problem = "你的名字叫什么"
        userBean.answer = "张三"
        userBean.type = UserEntity.PERSONAL
    }

    /**
     * 检查值不能为空
     */
    private fun checkUserNull(): Boolean {

        if (userBean.account.isBlank()) {
            toastK("账号不能为空")
            return true
        }
        if (userBean.password.isBlank()) {
            toastK("密码不能为空")
            return true
        }

        if (userBean.answer.isBlank()) {
            toastK("密保答案不能为空")
            return true
        }

        if (userBean.problem.isBlank()) {
            toastK("密保问题不能为空")
            return true
        }

        return false
    }

    /**
     * 登录
     */
    fun login() {
        service.login(userBean)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<UserEntity>() {
                override fun onSuccess(t: UserEntity) {
                    Constants.USER = t
                    actionLiveData.value = BaseActionEvent(VIEW_MODEL_LOGIN_SUCCESS)
                }
            })
    }

    /**
     * 注册
     */
    fun register() {
        if (checkUserNull()) return
        service.register(userBean)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<UserEntity>() {
                override fun onSuccess(t: UserEntity) {
                    userBean = t
                    actionLiveData.value = BaseActionEvent(VIEW_MODEL_REGISTER_SUCCESS)
                }
            })

    }

    /**
     * 修改密码
     */
    fun findPassword() {
        service.findPassword(userBean)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<UserEntity>() {
                override fun onSuccess(t: UserEntity) {
                    userBean = t
                    actionLiveData.value = BaseActionEvent(VIEW_MODEL_CHANGE_PASSWORD_SUCCESS)
                }
            })
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo() {
        service.getUserInfo(userBean)
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<UserEntity>() {
                override fun onSuccess(t: UserEntity) {
                    userBean = t
                    actionLiveData.value = BaseActionEvent(VIEW_MODEL_CHANGE_USER_INFO_SUCCESS)
                }
            })
    }


    /**
     * 获取用户接收到的信息
     */
    var messageList: MutableLiveData<List<LetterEntity>> = MutableLiveData()
    fun getReceiver() {
        service.getReceiver(SimpleRequestBean(Constants.USER.id))
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<LetterEntity>>() {
                override fun onSuccess(t: List<LetterEntity>) {
                    messageList.value = t
                }
            })
    }

    /**
     * 获取字典信息
     */
    fun dict() {
        service.dict()
            .compose(RxJavaUtils.applySchedulers())
            .subscribe(object : HttpObserver<List<DictEntity>>() {

                override fun onSuccess(t: List<DictEntity>) {
                    Constants.DICT = t
                    val map = t.groupBy {
                        it.rootId
                    }
                    Constants.LIST.putAll(map)
                }
            })
    }
}


fun Fragment.getYearList(action:(dict:DictEntity) -> Unit){
    showPickerDialog(Constants.LIST[DictEntity.YEAR]!!,action)
}

fun Fragment.getDegreeList(action:(dict:DictEntity) -> Unit) {
    showPickerDialog(Constants.LIST[DictEntity.DEGREE]!!,action)
}

fun Fragment.getSalaryList(action:(dict:DictEntity) -> Unit) {
    showPickerDialog(Constants.LIST[DictEntity.SALARY]!!,action)
}

fun Fragment.showPickerDialog(
    list: List<DictEntity>,
    action: (dict: DictEntity) -> Unit
) {
    //条件选择器
    val pvOptions: OptionsPickerView<String> =
        OptionsPickerBuilder(activity,
            OnOptionsSelectListener { options1, _, _, v ->
                action(list[options1])
            }).build<String>()
    val flatMap = list.flatMap {
        listOf(it.value)
    }
    pvOptions.setPicker(flatMap)
    pvOptions.show()
}
