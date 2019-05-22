package com.sdq.qxq.ffmpegdemos.net

import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-21
 * @update: 2019-05-21
 * @version: 1.0
 */
object RetrofitUtil {
    var ffApi: FfApi? = null

    fun getServerUrl(): String {
        return "https://www.wanandroid.com"
    }

    fun getApi(): FfApi? {
        if (ffApi == null) {
            synchronized(this) {
                if (ffApi == null) {
                    val builder: Retrofit.Builder = Retrofit.Builder()
                    builder.baseUrl(getServerUrl())
                    builder.addConverterFactory(GsonConverterFactory.create())
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    builder.client(OkHttpClientUtil.getOkHttpClient())
                    val retrofit: Retrofit = builder.build()
                    ffApi = retrofit.create(FfApi::class.java)
                }
            }
        }
        return ffApi
    }

    fun crateGsonBuilder():GsonBuilder{
        var gsonBuilder:GsonBuilder= GsonBuilder()
        gsonBuilder.registerTypeAdapter(Boolean::class.java, booleanAsIntAdapter)
        gsonBuilder.registerTypeAdapter(Double::class.java,RetrofitUtil.DoubleTypeAdapter())
        gsonBuilder.registerTypeAdapter(Float::class.java,RetrofitUtil.FloatTypeAdapter())
        gsonBuilder.registerTypeAdapter(Long::class.java,RetrofitUtil.LongTypeAdapter())
        gsonBuilder.registerTypeAdapter(Int::class.java,RetrofitUtil.IntegerTypeAdapter())
        return gsonBuilder
    }

    val booleanAsIntAdapter:TypeAdapter<Boolean> =object :TypeAdapter<Boolean>(){
        override fun write(out: JsonWriter?, value: Boolean?) {
            if (value==null){
                out?.nullValue()
            }else{
                out?.value(value)
            }
        }

        override fun read(`in`: JsonReader?): Boolean {
            var peek:JsonToken= `in`!!.peek()
            when(peek){
                JsonToken.BOOLEAN->return `in`.nextBoolean()
                JsonToken.NULL->return `in`.nextNull()==null
                JsonToken.NUMBER->return `in`.nextInt()!=0
                JsonToken.STRING->return `in`.nextString().toBoolean()
                else ->{
                    throw IllegalStateException("Expected BOOLEAN or NUMBER but was $peek")
                }
            }
        }

    }

    class DoubleTypeAdapter:TypeAdapter<Double>(){
        override fun write(out: JsonWriter?, value: Double?) {
             if (value==null){
                 out?.nullValue()
             }else{
                 out?.value(value)
             }
        }

        override fun read(`in`: JsonReader?): Double? {
            if (`in`?.peek()==JsonToken.NULL){
                `in`?.nextNull()
                return null
            }
            val stringValue:String= `in`?.nextString()!!
            return stringValue.toDouble()
        }

    }
    class IntegerTypeAdapter:TypeAdapter<Int>(){
        override fun write(out: JsonWriter?, value: Int?) {
            if (value==null){
                out?.nullValue()
            }else{
                out?.value(value)
            }
        }

        override fun read(`in`: JsonReader?): Int? {
            if (`in`?.peek()==JsonToken.NULL){
                `in`?.nextNull()
                return null
            }
            val stringValue=`in`?.nextString()
            return stringValue?.toInt()
        }

    }
    class FloatTypeAdapter:TypeAdapter<Float>(){
        override fun write(out: JsonWriter?, value: Float?) {
            if (value==null){
                out?.nullValue()
            }else{
                out?.value(value)
            }
        }


        override fun read(`in`: JsonReader?): Float? {
            if (`in`?.peek()==JsonToken.NULL){
                `in`?.nextNull()
                return null
            }
            val stringValue=`in`?.nextString()
            return stringValue?.toFloat()
        }

    }
    class LongTypeAdapter:TypeAdapter<Long>(){
        override fun write(out: JsonWriter?, value: Long?) {
            if (value==null){
                out?.nullValue()
            }else{
                out?.value(value)
            }
        }


        override fun read(`in`: JsonReader?): Long? {
            if (`in`?.peek()==JsonToken.NULL){
                `in`?.nextNull()
                return null
            }
            val stringValue=`in`?.nextString()
            return stringValue?.toLong()
        }

    }
}