package com.sdq.qxq.ffmpegdemos.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import com.sdq.qxq.ffmpegdemos.presenter.BasePresenter
import com.sdq.qxq.ffmpegdemos.view.IBaseView
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper





/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
 abstract class BaseActivity :AppCompatActivity(),IBaseView,BGASwipeBackHelper.Delegate{
//    val mActivityHeight:Int =getDimensionPixelSize(R.dimen.abc_action_bar_content_inset_material)
//    get() {
//        return field
//    }
//    fun getDimensionPixelSize(@DimenRes resId: Int): Int {
//        return resources?.getDimensionPixelSize(resId)
//    }
protected var mSwipeBackHelper: BGASwipeBackHelper? = null
    var mRootView: ViewGroup? = null
    get() {
        return field
    }
    var mTitleContainer: LinearLayout? = null
    get() {
        return field
    }
    var mToolbar: Toolbar? = null
    get() {
        return field
    }
    var mBasePresenter: BasePresenter<IBaseView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initSwipeBackFinish()
        super.onCreate(savedInstanceState)
        initPresenter()

    }
    fun initPresenter(){
        if (mBasePresenter==null){
            mBasePresenter=onCratePresenter()
        }
        if (mBasePresenter!=null){
            mBasePresenter!!.attachView(this)
        }
    }

    abstract fun onCratePresenter(): BasePresenter<IBaseView>?

    fun crateViewFrame(savedInstanceState: Bundle?){
        var inflater:LayoutInflater= LayoutInflater.from(this)
        mRootView=onCreateRootView(inflater,savedInstanceState)
    }

    private fun onCreateRootView(inflater: LayoutInflater, savedInstanceState: Bundle?): ViewGroup? {
        return inflater.inflate(com.sdq.qxq.ffmpegdemos.R.layout.base_activity_frame,null) as ViewGroup?
    }

    override fun getContext(): Context {
        return this
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private fun initSwipeBackFinish() {
        mSwipeBackHelper = BGASwipeBackHelper(this,this)

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper?.setSwipeBackEnable(true)
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper?.setIsOnlyTrackingLeftEdge(true)
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper?.setIsWeChatStyle(true)
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper?.setShadowResId(com.sdq.qxq.ffmpegdemos.R.drawable.bga_sbl_shadow)
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper?.setIsNeedShowShadow(true)
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper?.setIsShadowAlphaGradient(true)
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper?.setSwipeBackThreshold(0.3f)
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper?.setIsNavigationBarOverlap(false)
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    override fun isSupportSwipeBack(): Boolean {
        return true
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    override fun onSwipeBackLayoutSlide(slideOffset: Float) {}

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    override fun onSwipeBackLayoutCancel() {}

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    override fun onSwipeBackLayoutExecuted() {
        mSwipeBackHelper?.swipeBackward()
    }

    override fun onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper?.isSliding()!!) {
            return
        }
        mSwipeBackHelper?.backward()
    }

}