package com.sdq.qxq.ffmpegdemos

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask.execute
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.util.ObjectsCompat
import android.view.View
import android.view.Window
import android.widget.Button
import com.sdq.qxq.ffmpegdemos.goto
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlin.math.cos
import kotlin.math.max
import kotlin.properties.Delegates

class KotlinActivity : AppCompatActivity() {
    val ui = Ui() + UiOP.Show + UiOP.TranslateX(20f) + UiOP.TranslateY(20f) + UiOP.Hide
    private lateinit var btnClick: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        //创建一个对象
        var kt = KotlinTest(18, 12)
        kt.city = "广州"
        kt.name = "lenna"
        kt.test()
        var myclas = MyClass()
        //扩展函数
        myclas.Print()
        val l = mutableListOf(1, 2, 4)
        l.swap(0, 2)
        println(l.toString())
        val g = arrayListOf(1, 3, 5)
        g.swap(0, 2)

        println("no:${MyClass.no}")
        MyClass.foo()
        MyClass.Companion.goto()

        //数据类
        val user = User(name = "lennasu")
        println(user)
        val copyUser = user.copy(name = "newlennasu")
        println(copyUser)


        //密封类使用
//        run(,ui)

        //泛型使用
        val boss: Boss<Person> = Boss(Person("lennasu", 25))
        println(boss.value)
        val bos = Boss(Person("lennasu", 25))
//        bos.sort()

        //枚举类使用
//        ItemColor.valueOf("blak")
        btnOnclick.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("点击测试")
            }

        })

        val e = Example()
        println(e.p)

        e.p = "Lenna"
        println(e.p)
//        Delegates.observable(1,)


        val users = UserBean()
        users.name = "第一次赋值"
        users.name = "第二次赋值"

        //旧值：初始值 -> 新值：第一次赋值
        //旧值：第一次赋值 -> 新值：第二次赋值

        //属性存储映射
        //构造函数接受一个映射参数
        val bUser = BeaconUser(mapOf(
                "name" to "jon",
                "age" to 39
        ))
        //委托属性会从这个映射中取值（通过字符串键--属性的名称）
        println(bUser.name)
        println(bUser.age)
        //也适用于var属性，如果把只读的Map换成MutableMap的化：

        val strings = arrayOf("1212", "1121ssdsd")
//       val sum={x:Int,y:Int->x+y}
        strings.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }
        val map = mapOf(1 to "one ", 2 to "two")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach { _, value -> println("$value!") }
        }
        val oneMillion =1_000_000
        cos(1.2)


    }


    fun mains() {
        val b = BaseImpl(10)
        Derived(b).print()
    }

    fun run(view: View, ui: Ui) {
        val it = null
//        ui.uiOps.forEach(execute(view,it))
    }
//    fun execute(view: View, op: UiOP): (UiOP) -> Unit = when (op) {
//        UiOP.Show -> view.visibility = View.VISIBLE
//        UiOP.Hide -> view.visibility = View.GONE
//        is UiOP.TranslateX -> view.translationX = op.px // 这个 when 语句分支不仅告诉 view 要水平移动，还告诉 view 需要移动多少距离，这是枚举等 Java 传统思想不容易实现的
//        is UiOP.TranslateY -> view.translationY = op.px
//    }

    fun MyClass.Print() {
        print("用户名：$name")
    }

    fun MyClass.Companion.foo() {
        println("伴随对象的扩展函数")
    }

    val MyClass.Companion.no: Int
        get() = 10

    //扩展函数swap，调换不同位置的值
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun Array<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun Any?.toString(): String {
        if (this == null) return "null"
        //空检测之后"this"会自动转换为非空类型，所以下面的toString（）
        //调用类成员函数
        return toString()
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
    fun testTraining() {
        var itemss = arrayOf(11, 2, 4, 6, 7, 8)
        //itemss.indices 这个是数组的指标范围
        for (i in itemss.indices) {
            //那就可以通过索引轮训数组
            println(itemss[i])
        }
        //还可以这么搞
        for ((index, value) in itemss.withIndex()) {
            println("位置：$index 值：$value")
        }
    }

    /**
     * 集合遍历
     */
    fun testList() {
        //list 初始化
        var itemList = listOf(1, 2, 4, 56, 567, 754, 2, 34, 523, 334)
        for (item in itemList) {
            println(item)
        }
        //itemList.indices 有效指标集合，就是我们集合下标的集合，我们可以进行轮训
        for (index in itemList.indices) {
            println("集合下标：$index 对应的值：${itemList[index]}")
        }
    }

    /**
     * while 和do...while()
     */
    fun testWhile() {
        var a = 10
        //while 循环
        while (a > 0) {
            a--
        }
        var b = 8
        //do...while() 循环
        do {
            b--
        } while (b > 0);


        for (i in 1..5) {
            if (i == 2) {
                continue
            }
            if (i == 3) {
                break
            }
        }
    }

    companion object {
        fun actionStartActivity(activity: Activity) {
            var intent = Intent(activity, KotlinActivity::class.java)
            activity.startActivity(intent)
        }
    }

    fun kotlinToJava(source: ArrayList<Int>) {
        val list = ArrayList<Int>()
        for (item in source) {
            list.add(item)
        }
        for (i in 0..source.size - 1) {
            list[i] = source[i]
        }

    }
}


