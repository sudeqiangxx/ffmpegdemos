package com.sdq.qxq.ffmpegdemos;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @description:
 * @author: lenna
 * @date: 2019/5/6
 * @update: 2019/5/6
 * @version: 1.0
 */
public class VideoView extends SurfaceView {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec");
//        System.loadLibrary("avdevice");
        System.loadLibrary("avfilter");
        System.loadLibrary("avformat");
        System.loadLibrary("avutil");
//        System.loadLibrary("postproc");
        System.loadLibrary("swresample");
        System.loadLibrary("swscale");
    }
    public VideoView(Context context) {
        this(context, null);
    }

    public VideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.RGBA_8888);
    }

    public native void render(String input, Surface surface);

    public void palyer(final String inputString) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                render(inputString, VideoView.this.getHolder().getSurface());
            }
        }.start();
    }
}
