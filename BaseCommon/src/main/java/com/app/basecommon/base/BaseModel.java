package com.app.basecommon.base;

import android.app.Application;


import com.app.basecommon.http.NetWork;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


/**
 * @Auther JiRui
 * @CreateTime 2019/4/25 10:41
 * @Describe
 */
public class BaseModel extends AndroidViewModel {

    public BaseModel(@NonNull Application application) {
        super(application);
    }

    public <T> T sendRequest(String url, Class<T> apiServer){
        return NetWork.getInstance()
                .setTag(url)
                .getApiService(apiServer);
    }
}
