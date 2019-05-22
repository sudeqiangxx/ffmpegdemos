package com.sdq.qxq.ffmpegdemos.net;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @description: references: [利用logger打印完整的okhttp网络请求和响应日志 - 简书](https://www.jianshu.com/p/e044cab4f530)
 * @author: Kenny
 * @date: 2019-05-10 18:39
 * @version: 1.0
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {

    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtils.formatJson(JsonUtils.decodeUnicode(message));
        }
        mMessage.append(message.concat("\n"));
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtils.d(mMessage.toString());
        }
    }
}
