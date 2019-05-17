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
 fun main(args:Array<String>){
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
//    coroutineScope {
//        launch {
//            delay(500L)
//            println("我在coroutineScope中的launch")
//        }
//        delay(100L)
//        println("我在coroutineScope中")
//    }
//    println("hh-----")
//    testMain()
    testFinally()


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

//挂起函数
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}


fun testMain(){
    runBlocking {
        val startTime=System.currentTimeMillis()
        val job=launch(Dispatchers.Default){
            var nextPrintTIme=startTime
            var i=0
            while (i<5){
                if (System.currentTimeMillis()>=nextPrintTIme){
                    println("job : I`m sleeping ${i++} ...")
                    nextPrintTIme+=500L
                }
            }
        }
        delay(1300L)
        println("main: I`m tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit .")

    }

}
fun testMains(){
    runBlocking {
        val startTime=System.currentTimeMillis()
        val job=launch(Dispatchers.Default){
            var nextPrintTIme=startTime
            var i=0
            while (isActive){
                if (System.currentTimeMillis()>=nextPrintTIme){
                    println("job : I`m sleeping ${i++} ...")
                    nextPrintTIme+=500L
                }
            }
        }
        delay(1300L)
        println("main: I`m tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit .")

    }

}

fun testFinally(){
    runBlocking {
        val job=launch {
            try {
                repeat(1000){
                    i->
                    println("job: I`m sleeping $i")
                    delay(500L)
                }
            }finally {
                println("job:I `m running finally")
            }

        }

        delay(1300L)
        println("main:I`m tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit.")
    }

}
