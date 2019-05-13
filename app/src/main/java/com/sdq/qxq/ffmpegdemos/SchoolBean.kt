package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
class SchoolBean {

}
open  class Ss(x:Int){
    public open val y:Int=x
}
interface B{

}
val ab:Ss=object :Ss(1), B{
    override val y=5
}

fun main(args:Array<String>){
    val site=object {
        var name:String="hh"
        var age:Int=12
    }
    println(site.name)
    println(site.age)
}