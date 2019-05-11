package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019/5/11
 * @update: 2019/5/11
 * @version: 1.0
 */
 open class Person(var name:String ,var age:Int) {

    open fun gotoSchool(){
        print("父类调用")
    }
}