package com.sdq.qxq.ffmpegdemos

import kotlinx.coroutines.*
import kotlin.math.log

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-15
 * @update: 2019-05-15
 * @version: 1.0
 */
suspend fun main(args:Array<String>){
//    GlobalScope.launch {
//        delay(5000L)
//        println("World!")
//    }
//    println("Hello,")
//    Thread.sleep(8000L)
//
//
//    //
//    GlobalScope.launch {
//        //协程
//        delay(2000L)
//        println("World!")
//    }
//    println("Hello,")
//    //阻塞主线程
//    runBlocking {
//        delay(20000L)//延迟2秒保活jvm
//    }
////    mains()
////    println("结束了------------")
//    println("Hello,")
//    val job=GlobalScope.launch {
//        delay(2000L)
//        println("World!")
//    }
//    println("Hello,")
//    job.join()
//    println("结束了------------")

//    mas()
//    newMain()
    coroutineScope {
        launch {
            delay(500L)
            println("我在coroutineScope中的launch")
        }
        delay(100L)
        println("我在coroutineScope中")
    }
    println("hh-----")
}

fun mains()= runBlocking {
    GlobalScope.launch {
        delay(2000L)
        println("World!")
    }
    println("Hello,")
    delay(3000L)
}

fun mas()= runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

fun newMain()= runBlocking {
    launch {
        delay(200L)
        println("开始执行-----我在launch")
    }
    coroutineScope {
        launch {
            delay(500L)
            println("我在coroutineScope中的launch")
        }
        delay(100L)
        println("我在coroutineScope中")
    }
    println("完毕------")
}

fun newMain1()= runBlocking {
    launch {
//        doWorld()
    }
}
