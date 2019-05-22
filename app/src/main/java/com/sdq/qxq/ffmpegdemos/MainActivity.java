package com.sdq.qxq.ffmpegdemos;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdq.qxq.ffmpegdemos.net.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.init(true);
        findViewById(R.id.goto_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,CanvasBitmapActivity.class);
//                startActivity(intent);

                RxPermissions rxPermissions=new RxPermissions(MainActivity.this);
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE
                        ,Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ,Manifest.permission.READ_PHONE_STATE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean){
//                                    fileLengths("/storage/emulated/0/beacon/files/circle/crypt.jpeg","/storage/emulated/0/beacon/files/circle/decrypt.jpeg");
                                    KotlinActivity.Companion.actionStartActivity(MainActivity.this);
                                }
                            }
                        });
            }
        });

        // Example of a call to a native method
//        TextView tv = findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
        stringFromJNI();
        Button play = findViewById(R.id.sample_text);
        final VideoView videoView = findViewById(R.id.videoview);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.palyer("http://tb-video.bdstatic.com/tieba-smallvideo/68_20df3a646ab5357464cd819ea987763a.mp4");
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String codeR();

    /**
     * 解码
     * @param inputurl
     * @param outPuturl
     * @return
     */
    public native int decode(String inputurl,String outPuturl);

    public native void fileLengths(String path,String crypt_path);

    private static final Class<?>[] constructorParams={InvocationHandler.class};
}
