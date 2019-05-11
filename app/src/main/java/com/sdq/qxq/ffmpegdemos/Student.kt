package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019/5/11
 * @update: 2019/5/11
 * @version: 1.0
 */
class Student : Person, PersonB{
    override val count: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    //调用主构造函数
    constructor(name:String ,age:Int, phone :Int):super(name,age){
        print("name=$name age=$age  phone=$phone")
    }
    //调用另外一个次构造函数
    constructor(name:String,age:Int,phone:Int,qq:Int):this(name,age,phone){
        print("name=$name age=$age  phone=$phone qq=$qq")

    }

    /**
     * 重写父类中的方法
     */
    override fun gotoSchool() {
        //调用父类中的方法
        super.gotoSchool()
    }
}