package com.yoogor.newretail.buslibrary;

import java.lang.reflect.Method;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/7 17:04
 * @Describe
 */
public class MethodManager {

    // 方法接受的参数对象的类型(订阅者里面的参数类型)
    private Class<?> type;

    // 线程模式
    private ThreadMode threadMode;

    // 方法本身
    private Method method;

    public MethodManager(Class<?> type, ThreadMode threadMode, Method method) {
        this.type = type;
        this.threadMode = threadMode;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public Method getMethod() {
        return method;
    }
}
