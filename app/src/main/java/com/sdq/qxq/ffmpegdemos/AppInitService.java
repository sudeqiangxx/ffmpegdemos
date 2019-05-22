package com.sdq.qxq.ffmpegdemos;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.sdq.qxq.ffmpegdemos.net.LogUtils;
import com.tencent.smtt.sdk.QbSdk;


/**
 * @description: Application 初始化的服务
 * @author: Kenny
 * @date: 2019-05-11 18:04
 * @version: 1.0
 */
public class AppInitService extends IntentService {

    private static final String ACTION_INIT = "ApplicationInitService";

    public static void start(Context context) {
        Intent intent = new Intent(context, AppInitService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    public AppInitService() {
        super("ApplicationInitInitializedService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication(intent);
            }
        }
    }

    private void initApplication(Intent intent) {
        initX5Web();
    }

    private void initX5Web() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.d(" onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
