package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
open class ClassC {
    open fun ClassD.foo(){
        println("ClassD.foo in ClassC")
    }
    open fun ClassD1.foo(){
        println("D1.foo in C")
    }

    fun caller(d:ClassD){
        d.foo()
    }
}