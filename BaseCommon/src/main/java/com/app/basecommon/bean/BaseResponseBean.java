package com.app.basecommon.bean;

import java.io.Serializable;

/**
 * @Auther JiRui
 * @CreateTime 2019/4/28 9:52
 * @Describe
 */

public class BaseResponseBean<T> extends BaseEntityBean implements Serializable {
    public int code = 0;
    public String msg = "";
    public boolean success;
    public T data;

    public BaseResponseBean(int itemType) {
        super(itemType);
    }
}
