package com.sdq.qxq.ffmpegdemos.presenter

import com.sdq.qxq.ffmpegdemos.model.BaseHttpResponse
import com.sdq.qxq.ffmpegdemos.view.ILoadMoreView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
abstract class ILoadMorePresenter<T : BaseHttpResponse, V : ILoadMoreView> : RefreshPresenter<T, V>() {
    companion object {
        const val START_PAGE_INDEX: Int = 1
    }

    var mCurrentPage = START_PAGE_INDEX - 1
    var mHasMore: Boolean = true
        get() = field
    var mLoadMoreFailed: Boolean = false
        get() = field
    var mLoadMoreCall: Call<T>? = null
    var mLoadMoreCallBack: Callback<T>? = null
    var mRequestingPage: Int = 0
        get() {
            return field
        }

    fun loadMore() {
        if (!mLoading && !mHasMore && isViewAttached() && !mRefreshing) {
            if (mLoadMoreFailed) {
                getView()?.setLoadMoreFailedVisable(false)
            } else {
                getView()?.showLoadMoreView()
            }
            mRequestingPage = mCurrentPage + 1
            mLoadMoreCall = onPageRequest(mRequestingPage)
            if (mLoadMoreCall != null) {
                if (mLoadMoreCallBack == null) {
                    mLoadMoreCallBack = object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            //失败
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            //成功
                        }

                    }
                }
            }
        }
    }

    abstract fun onPageRequest(mRequestingPage: Int): Call<T>


}