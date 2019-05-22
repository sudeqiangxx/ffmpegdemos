package com.sdq.qxq.ffmpegdemos.net;

import android.content.pm.PackageInfo;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @description:
 * @author: Kenny
 * @date: 2019-05-11 18:54
 * @version: 1.0
 */
public class OkHttpClientUtil {
    private OkHttpClientUtil() {
        //no instance
    }

    public static final int HTTP_CONNECT_TIMEOUT = 30;
    public static final int HTTP_READ_TIMEOUT = 30;

    private static OkHttpClient sOkHttpClient;
    private static String sUserAgent;
    private static String sVersionName;
    private static String sChannelName;
    private static final String CLIENT_TYPE = "a";

    public static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (OkHttpClientUtil.class) {
                if (sOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();

//                    if (BuildConfigManager.isInDebugMode()) {
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(interceptor);
//                    }

                    builder.addInterceptor(new NetWorkInterceptor());
                    builder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
                    builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);

                    sOkHttpClient = builder.build();
                }
            }
        }
        return sOkHttpClient;
    }

    static class NetWorkInterceptor implements Interceptor {

        public Hashtable<String, String> headers;

        public NetWorkInterceptor() {

        }

        public NetWorkInterceptor(Hashtable<String, String> headers) {
            this.headers = headers;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            builder.addHeader("User-Agent", getUserAgent());
            builder.addHeader("Accept", "application/json");
            builder.addHeader("Content-type", "application/json;charset=UTF-8");
            //version,channel,timestamp,clientType

            builder.addHeader("cs", CLIENT_TYPE);// client system
            if (sVersionName == null) {
//                PackageInfo pi = AppManager.getInstance().getPackageInfo();
//                sVersionName = String.valueOf(pi.versionCode);
            }

            String appt = String.valueOf(System.currentTimeMillis() / 1000);
//            builder.addHeader("av", sVersionName);//app version
            builder.addHeader("at", appt);// app timestamp
//            String secret = request.header("secret");
//            secret = SecretUtil.decode(secret);
            builder.removeHeader("secret");

            //    TODO 头参数增加token
//            String token = AppManager.getInstance().getToken();
//            if (!StringUtil.isEmpty(token)) {
//                builder.addHeader("token", token);
//            }

//            String did = BeaconApp.getInstance().getDeviceId();
//            String dt = BeaconApp.getInstance().getDeviceType();
//            String csv = BeaconApp.getInstance().getClientSysVersion();
//            String ac = BeaconApp.getInstance().getChannel();
//            String phm = BeaconApp.getInstance().getPhoneModel();


//            builder.addHeader("did", did);
//            builder.addHeader("dt", dt);
//            builder.addHeader("csv", csv);
//            builder.addHeader("ac", ac);
//            builder.addHeader("phm", phm);

//            String androidId = BeaconApp.getInstance().getAndroidId();
//            String imei = BeaconApp.getInstance().getImei();
//            String md5imei = BeaconApp.getInstance().getMd5Imei();

//            builder.addHeader("anId", androidId);
//            builder.addHeader("anImei", imei);
//            builder.addHeader("anMimei", md5imei);


            //at.cs.av.t.
//            String extraParams = did + appt + CLIENT_TYPE + sVersionName;


//            String apps = encryptSecret(extraParams);
//            builder.addHeader("sign", apps);// app secret

            if (headers != null && headers.size() > 0) {
                for (String key : headers.keySet()) {
                    builder.addHeader(key, headers.get(key));
                }
            }
            Request newRequest = builder.build();
            Response response = chain.proceed(newRequest);

//            int code = response.code();
//            if (code == HttpResponse.Status.TOKEN_INVALIDATE) {
//                BeaconApp.getInstance().onRequestFailToLogout(code);
//            }
            return response;
        }
    }

    private static String getUserAgent() {
        if (TextUtils.isEmpty(sUserAgent)) {
//            PackageInfo pi = AppManager.getInstance().getPackageInfo();
            StringBuilder ua = new StringBuilder();
//            ua.append(AppManager.getInstance().getApp().getPackageName());
//            ua.append("/ver_" + pi.versionCode);
            ua.append("/sdk_" + android.os.Build.VERSION.SDK_INT);
            sUserAgent = ua.toString();
        }
        sUserAgent = "";
        return sUserAgent;
    }

    private static String encryptSecret(String extraParams) {
//        String key = BuildConfigManager.getNetReqSalt();
//        return SimpleEncryptUtil.getMD5Code(extraParams + key);
        //    TODO  获取key
        return "";
    }

}
