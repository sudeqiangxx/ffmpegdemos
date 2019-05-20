package com.sdq.qxq.ffmpegdemos.presenter

import android.widget.Toast
import com.sdq.qxq.ffmpegdemos.model.BaseHttpResponse
import com.sdq.qxq.ffmpegdemos.model.StringUtils
import com.sdq.qxq.ffmpegdemos.view.ILoadingView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
abstract class ILoadingPresenter<T : BaseHttpResponse, V : ILoadingView> : BasePresenter<V>() {
    var mLoading: Boolean
        get() {
            return mLoading
        }
        set(value) {
            mLoading = value
        }
    var mLoaded: Boolean
        get() {
            return mLoaded
        }
        set(value) {
            mLoaded = value
        }
    var mRequestCall: Call<T>? = null


    override fun detachView(isRetainInstance: Boolean) {
        super.detachView(isRetainInstance)
        cancelRequest()
    }

    fun start() {
        if (!mLoaded && !mLoading && isViewAttached()) {
            getView()?.showLoadingView()
            request()
        }
    }

    fun request() {
        if (mLoading) {
            cancelRequest()
            mRequestCall = onRequest()
            if (mRequestCall != null) {
                mLoading = true
                mRequestCall!!.enqueue(object : Callback<T> {
                    override fun onFailure(call: Call<T>, t: Throwable) {
                        t.printStackTrace()
                        if (isViewAttached()) {
                            onRequestFiled(t)
                        }
                        if (t is UnknownHostException) {
                            Toast.makeText(getView()?.getContext(), "网络不太好，请检查后重试", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        var data: T = response.body()!!
                        if (isViewAttached()) {
                            onResponse(data)
                        }
                        mLoaded = !isDataEmpty(data)
                        mLoading = false
                    }

                })
            }
        }
    }

    private fun onRequestFiled(t: Throwable) {
        getView()?.showReloadView()
    }

    abstract fun onRequest(): Call<T>

    fun cancelRequest() {
        if (mRequestCall != null) {
            mRequestCall?.cancel()
            mRequestCall = null
        }
        mLoading = false
    }

    fun onResponse(data: T) {
        if (isDataEmpty(data)) {
            onRequestEmpty(data)
        } else {
            if (shuldShowContentViewAfterDataLoaded()) {
                getView()?.showContentView()
            }
            bindViewData(data)
        }
    }

    abstract fun bindViewData(data: T)

    fun shuldShowContentViewAfterDataLoaded(): Boolean {
        return true
    }

    private fun onRequestEmpty(data: T) {
        if (isViewAttached()) {
            if (data != null) {
                var message: String = data.getMessage()
                if (StringUtils.isEmpty(message)) {
                    message = "暂时没有内容~"
                }

            } else {
                getView()?.showEmptyView("暂时没有内容~")
            }
        }
    }


    private fun isDataEmpty(data: T): Boolean {
        return data == null || !data.isResponseOk()
    }

}


