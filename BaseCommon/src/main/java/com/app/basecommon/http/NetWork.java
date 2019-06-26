package com.app.basecommon.http;

import android.text.TextUtils;


import com.app.basecommon.base.BaseApp;
import com.app.basecommon.common.Constant;
import com.app.basecommon.utiles.SPUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：CHENHAO
 */

public class NetWork {
    private static NetWork manager;
    private Retrofit retrofit;
    private static NetWorkConfig mNetWorkConfig;
    private final Retrofit.Builder mBuilder;

    public static void config(NetWorkConfig netWorkConfig){
        mNetWorkConfig = netWorkConfig;
    }

    /**
     * 单例模式
     */
    public static NetWork getInstance() {
        if (manager == null) {
            synchronized (NetWork.class) {
                if (manager == null) {
                    manager = new NetWork();
                }
            }
        }
        return manager;
    }

    /**
     * 私有构造方法
     */
    private NetWork() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constant.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(getUserAgentIntercepter())
                .build();

        mBuilder = new Retrofit.Builder();
        retrofit = mBuilder
                .baseUrl(mNetWorkConfig != null && !TextUtils.isEmpty(mNetWorkConfig.baseUrl)
                        ? mNetWorkConfig.baseUrl
                        : Constant.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
                        return new Converter<ResponseBody, Object>() {
                            @Override
                            public Object convert(ResponseBody body) throws IOException {
                                if (body.contentLength() == 0) return null;
                                return delegate.convert(body);
                            }
                        };
                    }
                })
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 返回一个请求的代理统一配置
     *
     * @return
     */
    private Interceptor getUserAgentIntercepter() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
//                String safeToken = UserHelper.getInstence().getUserInfo().getSafeToken();
                String safeToken = SPUtils.getInstance().getString(BaseApp.getApplication(),Constant.ACCESS_TOKEN,"");
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", TextUtils.isEmpty(safeToken) ? "" : "Bearer" + safeToken)
                        .addHeader("grantType", "password")
                        .addHeader("clientId", "webApp")
                        .addHeader("clientSecret", "123456")
                        .addHeader("scope", "webApp")
                        .addHeader("loginType", "3")
                        .build();
                return chain.proceed(request);
            }
        };
    }

    public <T> T getApiService(Class<T> apiServer) {
        return retrofit.create(apiServer);
    }

    //设置tag取消请求标签
    public NetWork setTag(String tag) {
        ApiCancleManager.getInstance().setTagValue(tag);
        return manager;
    }

    public NetWork setBaseUrl(String url){
        mBuilder.baseUrl(url);
        return manager;
    }
}
