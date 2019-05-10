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

    /**
     * 循环测试代码
     */
    fun testTraining(){
        var itemss= arrayOf(11,2,4,6,7,8)
        //itemss.indices 这个是数组的指标范围
        for (i in itemss.indices){
            //那就可以通过索引轮训数组
            println(itemss[i])
        }
        //还可以这么搞
        for ((index ,value) in itemss.withIndex()){
            println("位置：$index 值：$value")
        }
    }

    /**
     * 集合遍历
     */
    fun testList(){
        //list 初始化
        var itemList= listOf(1,2,4,56,567,754,2,34,523,334)
        for (item in itemList){
            println(item)
        }
        //itemList.indices 有效指标集合，就是我们集合下标的集合，我们可以进行轮训
        for (index in itemList.indices){
            println("集合下标：$index 对应的值：${itemList[index]}")
        }
    }

    /**
     * while 和do...while()
     */
    fun testWhile(){
        var a=10
        //while 循环
        while (a>0){
            a--
        }
        var b=8
        //do...while() 循环
        do {
            b--
        }while (b>0);


        for (i in 1..5){
            if (i==2){
                continue
            }
            if (i==3){
                break
            }
        }
    }
}
