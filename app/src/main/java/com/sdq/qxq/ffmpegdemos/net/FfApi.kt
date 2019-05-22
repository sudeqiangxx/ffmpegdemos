package com.sdq.qxq.ffmpegdemos.net

import com.sdq.qxq.ffmpegdemos.model.Banner
import com.sdq.qxq.ffmpegdemos.model.HttpResponse
import com.sdq.qxq.ffmpegdemos.model.Wxing
import retrofit2.Call
import retrofit2.http.GET

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-21
 * @update: 2019-05-21
 * @version: 1.0
 */
interface FfApi {
    @GET("/wxarticle/chapters/json")
    fun getResponse():Call<HttpResponse<Wxing>>
    @GET("/banner/json")
    fun getBanner():Call<HttpResponse<Banner>>
}