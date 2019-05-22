package com.sdq.qxq.ffmpegdemos.net;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @description:
 * @author: Kenny
 * @date: 2019-05-10 18:40
 * @version: 1.0
 */
public class LogUtils {
    private LogUtils() {
        //no instance
    }


    /**
     * 初始化log工具，在app入口处调用
     *
     * @param isLogEnable 是否打印log
     */
    public static void init(boolean isLogEnable) {
        if (isLogEnable) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }

    public static void d(String message) {
        Logger.d(message);
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message, Throwable e) {
        String info = e != null ? e.toString() : "null";
        Logger.w(message + "：" + info);
    }

    public static void e(String message, Throwable e) {
        Logger.e(e, message);
    }

    public static void json(String json) {
        Logger.json(json);
    }


}
