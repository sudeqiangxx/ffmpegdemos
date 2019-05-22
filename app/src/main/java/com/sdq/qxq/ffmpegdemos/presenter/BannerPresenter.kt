package com.sdq.qxq.ffmpegdemos.presenter

import com.sdq.qxq.ffmpegdemos.model.Banner
import com.sdq.qxq.ffmpegdemos.model.HttpResponse
import com.sdq.qxq.ffmpegdemos.net.RetrofitUtil
import com.sdq.qxq.ffmpegdemos.view.IBaseView
import com.sdq.qxq.ffmpegdemos.view.IHomeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
class BannerPresenter :BasePresenter<IHomeView>() {
    fun getBanner(){
        var requestBanner=RetrofitUtil.getApi()?.getBanner()
        requestBanner?.enqueue(object :Callback<HttpResponse<Banner>>{
            override fun onFailure(call: Call<HttpResponse<Banner>>, t: Throwable) {
            }

            override fun onResponse(call: Call<HttpResponse<Banner>>, response: Response<HttpResponse<Banner>>) {
                if (response.isSuccessful){
                    var datas=response.body()
                    var banners =datas?.data
                    if (banners != null) {
                        getView()?.onResponse(banners)
                    }

                }
            }

        })
    }
}