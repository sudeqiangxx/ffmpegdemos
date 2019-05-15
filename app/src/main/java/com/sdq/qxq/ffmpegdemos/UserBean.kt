package com.sdq.qxq.ffmpegdemos

import kotlin.properties.Delegates

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-14
 * @update: 2019-05-14
 * @version: 1.0
 */
class UserBean {
    var name:String by Delegates.observable("初始值"){
        prop,old,new ->
        println("旧值$old->新值：$new")
    }
}