package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-14
 * @update: 2019-05-14
 * @version: 1.0
 */
class BeaconUser(val map:Map<String,Any?>) {
    val name:String by map
    val age:Int by map
}