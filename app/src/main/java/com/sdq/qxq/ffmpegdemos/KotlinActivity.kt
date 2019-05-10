package com.sdq.qxq.ffmpegdemos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }

    //可以单独作为if 判断
    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    fun testWhen() {
        val items = setOf("11", "22", "3")
        when {
            "11" in items -> {
                print("打印" + 11)
            }
            "22" in items -> {

            }
            else -> {

            }
        }
    }
}
