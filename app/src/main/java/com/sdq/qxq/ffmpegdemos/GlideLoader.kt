package com.sdq.qxq.ffmpegdemos

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sdq.qxq.ffmpegdemos.model.Banner
import com.youth.banner.loader.ImageLoader

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
class GlideLoader :ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        //Glide 加载图片简单用法
        if (context != null) {
            if (imageView != null &&path is Banner) {

                Glide.with(context).load(path.imagePath).into(imageView)
            }
        }


    }

}