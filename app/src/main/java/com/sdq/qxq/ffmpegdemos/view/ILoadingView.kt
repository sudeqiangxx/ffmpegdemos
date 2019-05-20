package com.sdq.qxq.ffmpegdemos.view

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
interface ILoadingView :IBaseView {
    fun showLoadingView()
    fun showReloadView()
    fun showContentView()
    fun showEmptyView(text:String)
}