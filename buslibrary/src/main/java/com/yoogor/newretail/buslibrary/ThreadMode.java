package com.yoogor.newretail.buslibrary;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/7 17:01
 * @Describe
 */
public enum ThreadMode {
    POSTING, // 发布者在什么线程，订阅者方法就运行在什么线程
    MAIN, // 强制切换到主线程
    BACKGROUND, // 强制切换到子线程
}
