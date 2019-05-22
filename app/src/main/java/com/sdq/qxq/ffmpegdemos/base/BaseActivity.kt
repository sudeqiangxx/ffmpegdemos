package com.sdq.qxq.ffmpegdemos.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.DimenRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import com.sdq.qxq.ffmpegdemos.R
import com.sdq.qxq.ffmpegdemos.presenter.BasePresenter
import com.sdq.qxq.ffmpegdemos.view.IBaseView
import java.util.zip.Inflater

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-20
 * @update: 2019-05-20
 * @version: 1.0
 */
 abstract class BaseActivity :AppCompatActivity(),IBaseView{
//    val mActivityHeight:Int =getDimensionPixelSize(R.dimen.abc_action_bar_content_inset_material)
//    get() {
//        return field
//    }
//    fun getDimensionPixelSize(@DimenRes resId: Int): Int {
//        return resources?.getDimensionPixelSize(resId)
//    }
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
        return inflater.inflate(R.layout.base_activity_frame,null) as ViewGroup?
    }

    override fun getContext(): Context {
        return this
    }

}