package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
class Runoob<out A>(val a:A) {
    fun foo():A{
        return a
    }


}

fun main(args:Array<String>){
    var strCo:Runoob<String> = Runoob("a")
    var anyCo:Runoob<Any> =Runoob("b")
    anyCo=strCo
    println(anyCo.foo())
}