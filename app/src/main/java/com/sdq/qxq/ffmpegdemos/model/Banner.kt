package com.sdq.qxq.ffmpegdemos.model

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
data class Banner(var desc: String //详情
                  , var id: Int
                  , var imagePath: String //背景
                  , var isVisable: Int   //是否显示
                  , var order: Int       //排序
                  , var title: String    //标题
                  , var type: Int        //类型
                  , var url: String      //跳转url
) {
}