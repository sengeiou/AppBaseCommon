package com.app.navigator.api;

import android.app.Activity;
import android.os.Bundle;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/14 14:46
 * @Describe
 */
public interface ModuleServices {

    void startAct(Activity activity, String serviceType, Bundle bundle);

    void startActForResult(Activity activity, String serviceType, Bundle bundle);
}
