package com.sdq.qxq.ffmpegdemos

/**
 * @description: 泛型类
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
class Boss<T:Person>(t:T) {
    var value =t
    fun <T : Person> bossIn(value: T)=Boss(value)
    fun <T:Comparable<T>> sort(list:List<T>){

    }
    fun <T> copyWhenGreater(list: List<T>,threshold:T):List<String>
        where T:CharSequence,
            T:Comparable<T>{
        return list.filter { it>threshold }.map { it.toString()}
    }
}