package com.sdq.qxq.ffmpegdemos.model

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
interface IHttpResponse :IEntity {
    fun getMessage():String
    fun getStatusCode():Int
    fun isResponseOk():Boolean
}