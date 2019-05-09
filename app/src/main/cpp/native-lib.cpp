#include <jni.h>
#include <string>
#include <android/log.h>
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
//一般大一点的gif都是通过c来一帧一帧的读取展现在surfaceView上的
//extern "C"
//{
//#include "libavcodec/avcodec.h"
//};
extern "C" {
#include <libavcodec/avcodec.h>
//封装格式处理
#include "libavformat/avformat.h"
//像素处理
#include "libswscale/swscale.h"
#include <android/native_window_jni.h>
#include <unistd.h>
#define LOGE(FORMAT, ...) __android_log_print(ANDROID_LOG_ERROR,"LC",FORMAT,##__VA_ARGS__);
double add(double a, double b) {
    return a + b;
}
double minus(double a, double b) {
    return a - b;
}
double divide(double a, double b) {
    return a / b;
}

//综合数据类型，结构体类型，内部构造复杂，类似于JavaScript变量赋值，
struct man {

    //年龄
    int age;
    //姓名
    char name[20];

    //函数指针
    int (*fun_p)();

};
//结构体的几种写法
//什么叫字节对齐，就是一个结构体内部的基本类型一般是不太一样的，
// 这样就是是字节对齐，填充字节，是最大基本类型的整数倍，比如一个结构体算出来是12  int 4，double 8，那他存储时就是16
//字符数组一旦赋值了，就不能再次用等号赋值，可以改变他的值
struct Man {
    int age;
    char name[20];
} mb, m2 = {25, "Jan"},*SP;
//其实这个SP是没有赋值的，那我们需要把它变成我们的需要引用的，SP=&m2;就是把我们的m2地址赋值给这个指针，能为我们所用，其实
//我们用的是这个m2结构体，


//联合体枚举

enum Day{
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Staturday,
    Sunday
};
extern "C"
void code_fun(double (*fun_p)(double a, double b), double_t m, double n) {
    double result = fun_p(m, n);
//除
//    printf("----------%c",msg);
    LOGE("开始学习函数指针 %f", result);
    struct man m1;
    mb.age = 20;
//    m1.name="lenna";
    strcpy(mb.name, "lenna");
    struct man ma = m1;
    SP=&m2;
    LOGE("开始学习函数指针 年龄=%d 性别=%s", mb.age, mb.name);
    LOGE("开始学习函数指针 年龄=%d 性别=%s", m2.age, m2.name);
    LOGE("开始学习函数指针 年龄=%d 性别=%s", SP->age, SP->name);



}

int *randArray() {
    int ids[10];
    srand((unsigned) time(NULL));
    for (int i = 0; i < 10; ++i) {
        ids[i] = rand();
        LOGE("开-----------%d", ids[i]);
    }
    int min = ids[0];
    for (int i = 0; i < 10; ++i) {
        if (ids[i] < min) {
            min = ids[i];
        }
    }
    LOGE("开最小值--%d", min);

    return ids;
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_sdq_qxq_ffmpegdemos_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    std::string hello = avcodec_configuration();
    avcodec_register_all();
    code_fun(divide, 3, 2);
    int *p = randArray();



    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_sdq_qxq_ffmpegdemos_MainActivity_codeR(JNIEnv *env, jobject instance) {

    std::string hello = avcodec_configuration();
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_sdq_qxq_ffmpegdemos_VideoView_render(JNIEnv *env, jobject instance, jstring input_,
                                              jobject surface) {

    const char *url = env->GetStringUTFChars(input_, 0);
    /**
     * 注册
     * 在4.0之后已经过期，可以直接忽略调用这个函数
     */
//    av_register_all();

    // 打开地址并获取里面的内容
    // avFormatContext是内容的一个上下文
    AVFormatContext *avFormatContext = avformat_alloc_context();
//    AVDictionary* options = NULL;
//    av_dict_set(&options, "rtsp_transport", "tcp", 0);
//    avformat_open_input(&avFormatContext, url, NULL, &options);
// 1.打开视频流的输入通道，
    avformat_open_input(&avFormatContext, url, NULL, NULL);
    //此步骤跟我们的网络，更我们的使用的网络协议有关，如果出现崩溃情况，应该从这几方面去找错误
    //2.寻找流，获取到流通道
    avformat_find_stream_info(avFormatContext, NULL);

    // 3.挑选出流，找出视频流
    int video_index = -1;
    for (int i = 0; i < avFormatContext->nb_streams; ++i) {
//        if (avFormatContext->streams[i]->codec->codec_type == AVMEDIA_TYPE_VIDEO) {
//            video_index = i;
//        }
        if (avFormatContext->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
            video_index = i;
        }
    }

    /**
     * 下面将进行解码、转换、绘制
     * 找到相应的流后，从流上找到解码器，然后打开解码器
     */
    // 获取解码器上下文
    AVCodecContext *avCodecContext = avFormatContext->streams[video_index]->codec;
    // 获取解码器
    AVCodec *avCodec = avcodec_find_decoder(avCodecContext->codec_id);
    // 打开解码器
    if (avcodec_open2(avCodecContext, avCodec, NULL) < 0) { //打开失败直接返回
        return;
    }
    /**
     * 申请AVPacket和AVFrame
     * AVPacket的作用是保存解码之前的数据和一些附加信息，例如显示时间戳(pts)、解码时间戳(dts)、数据时长和所在媒体流的索引等
     * AVFrame的作用是存放解码过后的数据
     */
    AVPacket *avPacket = static_cast<AVPacket *>(av_malloc(sizeof(AVPacket)));
    av_init_packet(avPacket);
    /**
     * 分配一个AVFrame结构体
     * AVFrame结构体一般用于存储原始数,指向解码后的原始帧
     */
    AVFrame *avFrame = av_frame_alloc();
    /**
     * 分配一个AVFrame结构体，指向存放转换成rgb后的帧
     */
    AVFrame *rgb_frame = av_frame_alloc();
    /**
     * rgb_frame是一个缓存区域，需要设置
     * 缓存区
     */
    uint8_t *out_buffer = static_cast<uint8_t *>(av_malloc(
            avpicture_get_size(AV_PIX_FMT_RGBA, avCodecContext->width, avCodecContext->height)));
    /**
     * 与缓存区关联
     * 设置rgb_frame缓存区
     */
    avpicture_fill((AVPicture *) rgb_frame, out_buffer, AV_PIX_FMT_RGBA, avCodecContext->width,
                   avCodecContext->height);
    /**
     * 需要一个ANativeWindow来进行原生绘制
     */
    ANativeWindow *pANativeWindow = ANativeWindow_fromSurface(env, surface);
    if (pANativeWindow == 0) { // 获取native window失败直接返回
        return;
    }

    SwsContext *swsContext = sws_getContext(avCodecContext->width, avCodecContext->height,
                                            avCodecContext->pix_fmt, avCodecContext->width,
                                            avCodecContext->height, AV_PIX_FMT_RGBA, SWS_BICUBIC,
                                            NULL, NULL, NULL);
    // 视频缓冲区
    ANativeWindow_Buffer nativeWindow_outBuffer;

    /**
     * 开始解码,
     */
    int frameCount;
    while (av_read_frame(avFormatContext, avPacket) >= 0) {
        if (avPacket->stream_index == video_index) {
            avcodec_decode_video2(avCodecContext, avFrame, &frameCount, avPacket);
            if (frameCount) {
                ANativeWindow_setBuffersGeometry(pANativeWindow, avCodecContext->width,
                                                 avCodecContext->height, WINDOW_FORMAT_RGBA_8888);
                /**
                 * 上锁
                 */
                ANativeWindow_lock(pANativeWindow, &nativeWindow_outBuffer, NULL);
                /**
                 * 转换成RGB格式
                 */
                sws_scale(swsContext, (const uint8_t *const *) avFrame->data, avFrame->linesize, 0,
                          avFrame->height, rgb_frame->data, rgb_frame->linesize);
                uint8_t *dst = static_cast<uint8_t *>(nativeWindow_outBuffer.bits);
                int destStride = nativeWindow_outBuffer.stride * 4;
                uint8_t *src = rgb_frame->data[0];
                int srcStride = rgb_frame->linesize[0];
                for (int i = 0; i < avCodecContext->height; i++) {
                    memcpy(dst + i * destStride, src + i * srcStride, srcStride);
                }
                ANativeWindow_unlockAndPost(pANativeWindow);

            }
        }
        av_free_packet(avPacket);
    }

    ANativeWindow_release(pANativeWindow);
    av_frame_free(&avFrame);
    av_frame_free(&rgb_frame);
    avcodec_close(avCodecContext);
    avformat_free_context(avFormatContext);

    env->ReleaseStringUTFChars(input_, url);
}

void custom_log(void *ptr, int level, const char *fmt, va_list vl) {
    FILE *fp = fopen("/storage/emulated/0/av_log.txt", "a+");
    if (fp) {
        vfprintf(fp, fmt, vl);
        fflush(fp);
        fclose(fp);
    }
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_sdq_qxq_ffmpegdemos_MainActivity_decode(JNIEnv *env, jobject instance, jstring inputurl_,
                                                 jstring outPuturl_) {
    const char *inputurl = env->GetStringUTFChars(inputurl_, 0);
    const char *outPuturl = env->GetStringUTFChars(outPuturl_, 0);
    AVFormatContext *pFormatCtx;
    int i, videoindex;
    AVCodecContext *pCodecCtx;
    AVCodec *pCodec;
    AVFrame *pFrame, *pFrameYUV;
    uint8_t *out_buffer;
    AVPacket *packet;
    int y_size;
    int ret, got_picture;
    struct SwsContext *img_convert_ctx;
    FILE *fp_yuv;
    int frame_cnt;
    clock_t time_start, time_finish;
    double time_duration = 0.0;
    char input_str[500] = {0};
    char output_str[500] = {0};
    char info[1000] = {0};
//    sprintf(input_str,"%s",(*env)->GetStringUTFChars(env,inputurl,NULL));
//初始化网络
    avformat_network_init();
    pFormatCtx = avformat_alloc_context();
    if (avformat_open_input(&pFormatCtx, input_str, NULL, NULL) != 0) {
        LOGE("Couldn't open input stream.");
        return -1;
    }
    if (avformat_find_stream_info(pFormatCtx, NULL) < 0) {
        return -1;
    }

    videoindex = -1;
    //找视频流
    for (int i = 0; i < pFormatCtx->nb_streams; ++i) {
        //判断是不是视频流，有可能是音频
        if (pFormatCtx->streams[i]->codec->codec_type == AVMEDIA_TYPE_VIDEO) {
            videoindex = i;
            break;
        }
    }
    //如果没有视频流就返回
    if (videoindex == -1) {
        return -1;
    }
    //找到视频流的解码器上下文，就是视频解码器处于的环境
    pCodecCtx = pFormatCtx->streams[videoindex]->codec;
    //获取视频解码器
    pCodec = avcodec_find_decoder(pCodecCtx->codec_id);
    if (pCodec == NULL) {
        return -1;
    }
    if (avcodec_open2(pCodecCtx, pCodec, NULL) < 0) {
        //打开解码器失败
        return -1;
    }
    pFrame = av_frame_alloc();
    pFrameYUV = av_frame_alloc();
    out_buffer = static_cast<uint8_t *>(av_malloc(
            avpicture_get_size(AV_PIX_FMT_RGBA, pCodecCtx->width, pCodecCtx->height)));
/**
     * 与缓存区关联
     * 设置rgb_frame缓存区
     */
    avpicture_fill((AVPicture *) pFrame, out_buffer, AV_PIX_FMT_RGBA, pCodecCtx->width,
                   pCodecCtx->height);
    /**
     * 需要一个ANativeWindow来进行原生绘制
     */
//    ANativeWindow *pANativeWindow = ANativeWindow_fromSurface(env, );
//    if (pANativeWindow == 0) { // 获取native window失败直接返回
//        return;
//    }
    packet = (AVPacket *) av_malloc(sizeof(AVPacket));
    img_convert_ctx = sws_getContext(pCodecCtx->width, pCodecCtx->height,
                                     pCodecCtx->pix_fmt, pCodecCtx->width,
                                     pCodecCtx->height, AV_PIX_FMT_RGBA, SWS_BICUBIC,
                                     NULL, NULL, NULL);
    fp_yuv = fopen(output_str, "wb+");
    if (fp_yuv == NULL) {
        return -1;
    }
    frame_cnt = 0;
    time_start = clock();
    while (av_read_frame(pFormatCtx, packet) >= 0) {
        if (packet->stream_index == videoindex) {
            ret = avcodec_decode_video2(pCodecCtx, pFrame, reinterpret_cast<int *>(got_picture),
                                        packet);
            if (ret < 0) {
                return -1;
            }
            if (got_picture) {
                sws_scale(img_convert_ctx, (const uint8_t *const *) pFrame->data, pFrame->linesize,
                          0, pCodecCtx->height, pFrameYUV->data, pFrameYUV->linesize);
                y_size = pCodecCtx->width * pCodecCtx->height;
                fwrite(pFrameYUV->data[0], 1, y_size, fp_yuv);
                fwrite(pFrameYUV->data[1], 1, y_size / 4, fp_yuv);
                fwrite(pFrame->data[2], 1, y_size / 4, fp_yuv);
                char picType_str[10] = {0};
                switch (pFrame->pict_type) {
                    case AV_PICTURE_TYPE_I:
                        sprintf(picType_str, "I");
                        break;
                    case AV_PICTURE_TYPE_B:
                        sprintf(picType_str, "B");
                        break;
                    case AV_PICTURE_TYPE_P:
                        sprintf(picType_str, "P");
                        break;
                    default:
                        sprintf(picType_str, "Other");
                        break;
                }
                frame_cnt++;
            }
        }
        av_free_packet(packet);
    }

    env->ReleaseStringUTFChars(inputurl_, inputurl);
    env->ReleaseStringUTFChars(outPuturl_, outPuturl);
}
///**
// * 获取文件大小
// */
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_sdq_qxq_ffmpegdemos_MainActivity_fileLength(JNIEnv *env, jobject instance, jstring path_) {
//    const char *path = env->GetStringUTFChars(path_, 0);
//    FILE *fp=fopen(path,"r");
//    fseek(fp,0,SEEK_END);
//    long  filesize=ftell(fp);
//    LOGE("filesize=%d",filesize);
//
//
//    env->ReleaseStringUTFChars(path_, path);
//}


/**
 * 对二进制文件进行加密
 * @param normal_path  源文件路径
 * @param crypt_path  加密后的文件路径
 * @param password  密码
 */
void crpypt(char normal_path[],char crypt_path[],char password[]){
    FILE *normal_fp=fopen(normal_path,"rb");
    FILE *crypt_fp=fopen(crypt_path,"wb");
    int ch ;
    int i=0;
    int pwd_len=strlen(password);
    while ((ch=fgetc(normal_fp))!=EOF){
        fputc(ch^password[i%pwd_len],crypt_fp);
        i++;
    }
    fclose(normal_fp);
    fclose(crypt_fp);
}

/**
 * 对二进制文件进行解密
 * @param crypt_path  加密后的文件路径
 * @param decrypt_path  解密后的文件路径
 * @param password  密码
 */
void decrpypt(char crypt_path[],char decrypt_path[],char password[]){
    FILE *crpypt_fp=fopen(crypt_path,"rb");
    FILE *decrpypt_fp=fopen(decrypt_path,"wb");
    int ch;
    int i=0;
    int pwd_len=strlen(password);
    while ((ch=fgetc(crpypt_fp))!=EOF){
        fputc(ch^password[i%pwd_len],decrpypt_fp);
        i++;
    }
    fclose(crpypt_fp);
    fclose(decrpypt_fp);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_sdq_qxq_ffmpegdemos_MainActivity_fileLengths(JNIEnv *env, jobject instance, jstring path_,
                                                      jstring crypt_path_) {
    const char *path = env->GetStringUTFChars(path_, 0);
    const char *crypt_path = env->GetStringUTFChars(crypt_path_, 0);
    char *_path=new char[strlen(path)+1] ;
    char *c_path=new char[strlen(crypt_path)+1];
    decrpypt(strcpy(_path,path),strcpy(c_path,crypt_path),"ilove");
    // TODO

    env->ReleaseStringUTFChars(path_, path);
    env->ReleaseStringUTFChars(crypt_path_, crypt_path);
}

}