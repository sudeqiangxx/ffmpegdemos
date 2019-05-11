package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019/5/10
 * @update: 2019/5/10
 * @version: 1.0
 */
class KotlinTest(var age: Int?, val dates: Int?) {
    //类体
    //定义属性
    var name:String="zhangshan"
    get() = field.toUpperCase()//赋值后转为大写
    set
    var city:String=""

    var no :Int =100
    get() = field //field 相当于就是no这个属性
    set(value) {
        if (value<10){
            field=value
        }else{
            field=-1
        }
    }

    var height:Int=180
    private set
    //定义一个类成员函数
    fun test(){
        print("我是类成员函数test")
        print("我是$name 我在$city")
    }
}