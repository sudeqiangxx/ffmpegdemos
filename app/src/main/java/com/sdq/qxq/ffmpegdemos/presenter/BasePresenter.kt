package com.sdq.qxq.ffmpegdemos.presenter

import com.sdq.qxq.ffmpegdemos.view.IView
import java.lang.ref.WeakReference
import java.util.*

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
open class BasePresenter<V : IView> : IPresenter<V> {
    lateinit var mViewReference: WeakReference<V>
    override fun attachView(view: V) {
        mViewReference = WeakReference<V>(view)
    }

    override fun detachView(isRetainInstance: Boolean) {
        mViewReference?.clear()
    }

    override fun getView(): V? {
        if (mViewReference == null) {
            return null
        } else {
            return mViewReference.get()
        }
    }

    override fun isViewAttached(): Boolean {
        return getView() != null
    }
}