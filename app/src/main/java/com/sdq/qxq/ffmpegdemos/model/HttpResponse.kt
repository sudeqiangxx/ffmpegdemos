package com.sdq.qxq.ffmpegdemos.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
data class HttpResponse<T>(var data: ArrayList<T>, var errorCode: Int, var errorMsg: String) : IHttpResponse, Serializable {

    override fun getMessage(): String {
        return errorMsg
    }

    override fun getStatusCode(): Int {
        return errorCode
    }

    override fun isResponseOk(): Boolean {
        return errorCode == 0
    }
}