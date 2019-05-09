package com.app.basecommon.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Auther JiRui
 * @CreateTime 2019/4/28 9:52
 * @Describe
 */
public abstract class BaseEntityBean implements MultiItemEntity {

    public static final int DEF_TYPE = 0;//默认的条目布局

    public int itemType;

    public BaseEntityBean(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
