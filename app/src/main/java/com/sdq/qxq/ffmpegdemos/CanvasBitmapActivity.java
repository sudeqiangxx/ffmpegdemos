package com.sdq.qxq.ffmpegdemos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CanvasBitmapActivity extends   AppCompatActivity{
    private Button mBtns;
    private ScrollView mSv;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannvas_bitmap);
        mBtns=findViewById(R.id.sumbit);
        mSv=findViewById(R.id.sv_bitmap);
        mBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File appDir = new File(Environment.getExternalStorageDirectory(), "saveImage");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File(appDir, fileName);

                shotScrollView(mSv,file.getAbsolutePath());
            }
        });
    }


    /**
     * 截屏scrollview成bitmap
     * @param scrollView
     * @return
     */

    public static Bitmap shotScrollView(ScrollView scrollView, String picpath) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(picpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
        }
        return bitmap;
    }

}
