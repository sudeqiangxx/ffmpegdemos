package com.sdq.qxq.ffmpegdemos.model

import com.sdq.qxq.ffmpegdemos.constans.NetCode

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
class BaseHttpResponse : IHttpResponse {
    var data: ArrayList<Any>? = null
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var errorCode: Int = 0
    var errorMessage: String = ""

    var httpStatus: Int = 0
    var code: Int = 0
    var msg: String = ""
    override fun getMessage(): String {
        return msg;
    }

    override fun getStatusCode(): Int {
        return code
    }

    override fun isResponseOk(): Boolean {
        return httpStatus == NetCode.STATUS_OK
    }
}