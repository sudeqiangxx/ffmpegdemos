package com.sdq.qxq.ffmpegdemos.wanandroid

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.sdq.qxq.ffmpegdemos.GlideLoader
import com.sdq.qxq.ffmpegdemos.R
import com.sdq.qxq.ffmpegdemos.base.BaseActivity
import com.sdq.qxq.ffmpegdemos.model.Banner
import com.sdq.qxq.ffmpegdemos.presenter.BannerPresenter
import com.sdq.qxq.ffmpegdemos.presenter.BasePresenter
import com.sdq.qxq.ffmpegdemos.view.IBaseView
import com.sdq.qxq.ffmpegdemos.view.IHomeView
import com.sdq.qxq.ffmpegdemos.wanandroid.activutil.ActivityUtil
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.activity_wan_android.*

class WanAndroidActivity : BaseActivity() ,IHomeView{
    override fun onCratePresenter(): BasePresenter<IBaseView>? {
        mBannerPresenter = BannerPresenter()
        return mBannerPresenter as BasePresenter<IBaseView>
    }

    override fun onResponse(banners: ArrayList<Banner>) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        banner.setImageLoader( GlideLoader())
        banner.setImages(banners)
        banner.setBannerAnimation(Transformer.DepthPage)
        banner.isAutoPlay(true)
        //设置轮播时间
        banner.setDelayTime(5000)
        var titles=ArrayList<String>()
        for (list in banners){
            titles.add(list.title)
        }
        banner.setBannerTitles(titles)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setIndicatorGravity(BannerConfig.CENTER)
        banner.setOnBannerListener(object :OnBannerListener{
            override fun OnBannerClick(position: Int) {
                var banner=banners.get(position)
                if (banner!=null){
                   ActivityUtil.startWebActivity(getContext() as Activity,banner.url)
                }
            }

        })
        banner.start()
    }

    lateinit var mBannerPresenter: BannerPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wan_android)
    }

    override fun onStart() {
        super.onStart()
        mBannerPresenter.getBanner()
    }

    companion object {
        fun startActivity(activity: Activity) {
            var intent = Intent(activity, WanAndroidActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
