package com.sdq.qxq.ffmpegdemos

import kotlinx.coroutines.*
import java.io.File
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.tan

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-15
 * @update: 2019-05-15
 * @version: 1.0
 */
fun main(args: Array<String>) {
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
//    testFinally()
//    testWithTimeOut()
//    testwithTimeoutOrNull()

//    println("d=$d")
//    val fruits= listOf("banana","avocado","apple","kiwifruit")
//    fruits.filter { it.startsWith("a") }
//            .sortedBy { it }
//            .forEach { println(it) }
//    println("fruits -----$fruits")

//    val p:String by lazy {
//
//    }
//    val files=File("test").listFiles()
//    println(files?.size)
//    一般我们用java一不小心就会出现声明null指针，当我们用了kotlin后只要加？就可以避免null指针的出现，不至于导致程序奔溃
//    println(files?.size?:"empty")
//    val values = mapOf(1 to "dd")
//    val email=values["email"]?:throw IllegalStateException("Email is missing!")

    var a = 1
    var b = 2
    a = b.also { b = a }
    println("a=$a")
    println("b=$b")
    if (a > b) {
        println("11111")
    } else {
        println("22222")
    }

}

object Resource {
    val name = "Name"
}


fun mains() = runBlocking {
    GlobalScope.launch {
        delay(2000L)
        println("World!")
    }
    println("Hello,")
    delay(3000L)
}

fun mas() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

fun newMain() = runBlocking {
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

fun newMain1() = runBlocking {
    launch {
        //        doWorld()

    }
}

//挂起函数
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}


fun testMain() {
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTIme = startTime
            var i = 0
            while (i < 5) {
                if (System.currentTimeMillis() >= nextPrintTIme) {
                    println("job : I`m sleeping ${i++} ...")
                    nextPrintTIme += 500L
                }
            }
        }
        delay(1300L)
        println("main: I`m tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit .")

    }

}

fun testMains() {
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTIme = startTime
            var i = 0
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTIme) {
                    println("job : I`m sleeping ${i++} ...")
                    nextPrintTIme += 500L
                }
            }
        }
        delay(1300L)
        println("main: I`m tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit .")

    }

}

fun testFinally() {
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I`m sleeping $i")
                    delay(500L)
                }
            } finally {
                println("job:I `m running finally")
            }

        }

        delay(1300L)
        println("main:I`m tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit.")
    }

}

fun testCancell() {
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job:I`m sleeping $i ...")
                    delay(500L)
                }
            } finally {

                withContext(NonCancellable) {
                    println("job:I`m running finally")
                    delay(1000L)
                    println("job :And I`ve just delayed for 1 sec bacause I`m non-cancellable")
                }
            }
        }
        delay(1300L)
        println("main: I`m tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit.")

    }
}

fun testWithTimeOut() {
    runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I`m sleeping $i ...")
                delay(500L)

            }
        }
    }
}

/**
 * 运行就不抛出异常了
 */
fun testwithTimeoutOrNull() {
    runBlocking {
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I`m sleeping $i")
                delay(500L)

            }
            "Done"
        }
        println("resutl is $result")

    }
}

