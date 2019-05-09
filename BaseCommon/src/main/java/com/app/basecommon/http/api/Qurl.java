package com.app.basecommon.http.api;


import com.app.basecommon.common.Constant;

/**
 * @author: zhengjr
 * @since: 2018/6/22
 * @describe:
 */

public interface Qurl {

    String HOST = Constant.BASE_URL;

    String API = "/api";

    //登录
    String login = API + "/login/login";

    //商品列表
    String findPage = API + "/appStoreGoods/appGoodsSpu/findPage";

}
