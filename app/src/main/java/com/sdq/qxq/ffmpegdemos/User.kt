package com.sdq.qxq.ffmpegdemos

import android.os.Parcel
import android.os.Parcelable

/**
 * @description: 数据类，编译器会自动的从主构造函数中根据所有生命的属性提取以下函数：equals（）/hashCode(),要实现数据类
 * 那必须遵守以下四个条件
 * 1.主构造函数中必须包含一个参数
 * 2.主构造函数中的参数必须带有val 或者var
 * 3.数据类不能声明为abstract 抽象的，和可继承的open，sealed或者inner
 * 4.数据类不能继承其他类，但是可以实现接口。
 *
 * 满足上面四条就能实现数据类，所以还是有一定的限制
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
data class User(val name: String) {


}