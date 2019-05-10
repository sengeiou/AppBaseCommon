package com.app.basecommon.navigator;

import android.content.Context;
import android.os.Bundle;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 14:57
 * @Describe
 */
public interface IModuleService {

    void register(String serviceType,IModuleService moduleService);

    void startAct(Context context, String serviceType, Bundle bundle);

}
