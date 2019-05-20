package com.sdq.qxq.ffmpegdemos.view

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
interface ILoadMoreView  :IRefreshView{
    fun showLoadMoreView()
    fun hideLoadMoreView()
    fun setLoadMoreFailedVisable(failed:Boolean)
    fun setViewHasMore(hasMore:Boolean)
}