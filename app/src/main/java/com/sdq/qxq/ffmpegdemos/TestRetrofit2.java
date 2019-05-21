package com.sdq.qxq.ffmpegdemos;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-21
 * @update: 2019-05-21
 * @version: 1.0
 */
public class TestRetrofit2 {
    public interface ISubject{
        void sayHello();
    }
    public static void main(String[] args){
        ISubject subject=(ISubject)Proxy.newProxyInstance(TestRetrofit2.class.getClassLoader(), new Class[]{ISubject.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("InvocationHandler$invoke: call"+method.getName());
                        return 0;
                    }
                });
        System.out.println("class of subject:"+subject.getClass().getName());
        System.out.println("super class of subject:"+subject.getClass().getSuperclass().getSimpleName());
        System.out.println("interface implemented by subject:"+ Arrays.toString(subject.getClass().getInterfaces()));
        System.out.println("fields of subject:"+Arrays.toString(subject.getClass().getDeclaredFields()));
        subject.sayHello();


    }
}
