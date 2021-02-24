package com.lxc.recruitment.data

import androidx.paging.PagingSource
import androidx.paging.rxjava3.RxPagingSource
import com.lxc.base.base.RxJavaUtils
import com.lxc.base.bean.SimpleRequestBean
import com.lxc.recruitment.api.HttpService
import com.lxc.recruitment.api.UserService
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.entity.LetterEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import java.io.IOException


class LetterDataSource(val service: UserService, val id: Long) : RxPagingSource<Int, LetterEntity>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, LetterEntity>> {
        return service.getReceiver(SimpleRequestBean(id))
            .compose(RxJavaUtils.applySchedulers())
            .lastOrError()
            .map<LoadResult<Int, LetterEntity>> {
                LoadResult.Page(it,1,1)
            }
    }

        //            .compose(RxJavaUtils.applySchedulers())
//             }
//            .subscribe(object : HttpObserver<List<LetterEntity>>() {
//                override fun onSuccess(t: List<LetterEntity>) {
//
//                }
//            })

}
