package com.sdq.qxq.ffmpegdemos

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
class ClassC1 :ClassC() {
    override fun ClassD.foo() {
        println("D.foo in C1")
    }

    override fun ClassD1.foo() {
        println("D1.foo in C1")
    }
}