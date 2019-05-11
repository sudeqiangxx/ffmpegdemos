package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019/5/11
 * @update: 2019/5/11
 * @version: 1.0
 * PersonA 是一般的类， PersonB 是接口，不能继承两个都是一般的类
 */
class Childs : PersonA(),PersonB {
    override val count: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    //重写父类中的属性
    override val age: Int
        get() = super.age


    override fun foof() {
        super<PersonA>.foof()
        super<PersonB>.foof()
    }

    
}