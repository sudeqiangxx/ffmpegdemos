package com.sdq.qxq.ffmpegdemos.model

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
data class Wxing(var children: ArrayList<Any>//子公众
                 , var courseId: Int
                 , var id: Int
                 , var name: String          //公众号名字
                 , var order: Int
                 , var parentChapterId: Int
                 , var userControlSetTop: Boolean
                 , var visable: Int) {
}