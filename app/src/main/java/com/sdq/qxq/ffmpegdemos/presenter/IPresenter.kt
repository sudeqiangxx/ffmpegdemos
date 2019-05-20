package com.sdq.qxq.ffmpegdemos.presenter

import com.sdq.qxq.ffmpegdemos.view.IView
import java.util.function.BinaryOperator

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
interface IPresenter<V :IView> {
    fun attachView(view:V)
    fun detachView(isRetainInstance:Boolean)
    fun getView():V?
    fun isViewAttached():Boolean
}