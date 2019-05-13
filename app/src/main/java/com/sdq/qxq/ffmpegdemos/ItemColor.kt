package com.sdq.qxq.ffmpegdemos

import android.graphics.ColorSpace

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
enum class ItemColor(val rgb: Int){
    RED(0xff0000),
    GREEN(0x00ff00),
    BLUE(0x0000ff)

}
enum class Color{
    RED,BLACK,BLUE,GREEN,WHITE
}

fun main(args:Array<String>){
    var color:Color=Color.BLUE
    val list=Color.values()
    for (l in list){
        println("我是：${l.name}")
    }
    println(Color.valueOf("RED"))
    //枚举名
    println(color.name)
    //定义的属性
    println(color.ordinal)

}