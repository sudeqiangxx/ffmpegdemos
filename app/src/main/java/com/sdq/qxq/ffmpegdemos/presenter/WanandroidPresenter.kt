package com.sdq.qxq.ffmpegdemos.presenter

import com.sdq.qxq.ffmpegdemos.model.HttpResponse
import com.sdq.qxq.ffmpegdemos.model.Wxing
import com.sdq.qxq.ffmpegdemos.net.LogUtils
import com.sdq.qxq.ffmpegdemos.net.RetrofitUtil
import com.sdq.qxq.ffmpegdemos.view.IBaseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-21
 * @update: 2019-05-21
 * @version: 1.0
 */
class WanandroidPresenter :BasePresenter<IBaseView>() {
    fun getWanAndroid(){
        var requestCall=RetrofitUtil.getApi()?.getResponse()
        requestCall?.enqueue(object :Callback<HttpResponse<Wxing>>{
            override fun onFailure(call: Call<HttpResponse<Wxing>>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<HttpResponse<Wxing>>, response: Response<HttpResponse<Wxing>>) {
                //成功
                var httpResponse=response.body()
                var wxingList=httpResponse?.data
                for (list in wxingList!!){
                    LogUtils.i("数据----->："+list.toString())

                }


            }

        })

    }

}