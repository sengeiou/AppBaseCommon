package com.app.testlib;

import android.text.TextUtils;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 16:22
 * @Describe
 */
public enum ActivityTag {

    //这里声明对外跳转的activity，也用规范自己跳转检查
    TESTACTICITY("TestActivity"), OTHERACTIVITY("OtherActivity");

    private String mTag;

    ActivityTag(String tag) {
        mTag = tag;
    }

    public String getTag() {
        return mTag;
    }

    public static ActivityTag searchValue(String tag) {
        if (TextUtils.equals(TESTACTICITY.mTag, tag)) {
            return TESTACTICITY;
        } else if (TextUtils.equals(OTHERACTIVITY.mTag, tag)) {
            return OTHERACTIVITY;
        }
        return TESTACTICITY;//默认返回值
    }
}

