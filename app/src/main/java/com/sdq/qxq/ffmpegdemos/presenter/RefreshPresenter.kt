package com.sdq.qxq.ffmpegdemos.presenter

import com.sdq.qxq.ffmpegdemos.model.BaseHttpResponse
import com.sdq.qxq.ffmpegdemos.view.IRefreshView
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
abstract class RefreshPresenter<T : BaseHttpResponse, V : IRefreshView> : ILoadingPresenter<T, V>() {
    open var mRefreshing: Boolean = false
        get() {
            return mRefreshing
        }
        set(value) {
            field = value
            if (isViewAttached()) {
                getView()?.setRefreshing(value)
            }
        }
    var mRefreshCall: Call<T>? = null
    var mRefreshCallBack: Callback<T>? = null


    override fun detachView(isRetainInstance: Boolean) {
        super.detachView(isRetainInstance)
        cancelRefresh()
    }

    private fun cancelRefresh() {
        if (mRefreshCall != null) {
            mRefreshCall?.cancel()
            mRefreshCall = null
        }
        mLoading = false

    }

    fun refresh() {
        if (!mLoading && !mRefreshing) {
            cancelRefresh()
            mRefreshCall = onRefreshRequest()
            if (mRefreshCall != null) {
                if (mRefreshCallBack == null) {
                    mRefreshCallBack = object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            t.printStackTrace()
                            if (isViewAttached()){
                                onRefreshFailed(t)
                            }
                            mLoading=false
                            mRefreshing=false
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            var data:T= response.body()!!
                            if (isViewAttached()){
                                onRefreshResponse(data)
                            }
                            mLoading=false
                            mRefreshing=false

                        }

                    }

                }
                mLoading=true
                mRefreshCall?.enqueue(mRefreshCallBack)
            }
        }
    }
    fun silentRefresh(){
        if (!mLoading&&!mRefreshing){
            var refreshCall=onRefreshRequest()
            if (refreshCall!=null){
                if (mRefreshCallBack==null){
                    mRefreshCallBack= object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            t.printStackTrace()
                            if (isViewAttached()){
                                onRefreshFailed(t)
                            }
                            mLoading=false
                            mRefreshing=false
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            var data:T= response.body()!!
                            if (isViewAttached()){
                                onRefreshResponse(data)
                            }
                            mLoading=false
                            mRefreshing=false

                        }

                    }
                    mLoading=true
                    refreshCall.enqueue(mRefreshCallBack)
                }
            }
        }
    }

    abstract fun onRefreshResponse(data: T)

    private fun onRefreshFailed(t: Throwable) {

    }
    abstract fun onRefreshRequest(): Call<T>?


}