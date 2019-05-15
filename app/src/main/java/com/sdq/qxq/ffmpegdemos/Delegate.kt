package com.sdq.qxq.ffmpegdemos

import kotlin.reflect.KProperty

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-14
 * @update: 2019-05-14
 * @version: 1.0
 */
class Delegate {
    operator fun getValue(thisRef:Any?,property:KProperty<*>):String{
        return "$thisRef,这里委托了${property.name}属性"
    }
    operator fun setValue(thisRef: Any?,property: KProperty<*>,value:String){
        println("$thisRef 的 ${property.name}属性赋值为$value")
    }
}

