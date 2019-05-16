package com.sdq.qxq.ffmpegdemos

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @description:
 * @author: lenna
 * @date: 2019/5/10
 * @update: 2019/5/10
 * @version: 1.0
 */
class KotlinTest(var age: Int?, val dates: Int?) {

    constructor( name:String?, age:Int?, dates:Int?) : this(age,dates) {

    }
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


    fun main()= runBlocking {
        val job=launch {
            repeat(1000){
                i->
                println("job:I`m sleeping $i")
                delay(500)
            }

        }
        delay(1300L)
        println("main:I`m tired of waiting")
        job.cancel()
        job.join()

    }
}