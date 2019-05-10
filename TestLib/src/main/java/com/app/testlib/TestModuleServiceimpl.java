package com.app.testlib;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.app.basecommon.navigator.IModuleService;
import com.app.basecommon.utiles.Logger;
import com.app.basecommon.utiles.Utils;

import java.util.HashMap;

import androidx.fragment.app.Fragment;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 15:31
 * @Describe
 */
public class TestModuleServiceimpl implements IModuleService {


    private final String TAG = "TestModuleServiceimpl";
    private static TestModuleServiceimpl mTestModuleService;

    public static TestModuleServiceimpl getInstance() {
        if (mTestModuleService == null) {
            synchronized (TestModuleServiceimpl.class) {
                if (mTestModuleService == null) {
                    mTestModuleService = new TestModuleServiceimpl();
                }
            }
        }
        return mTestModuleService;
    }

    @Override
    public void register(String serviceType, IModuleService moduleService) {
    }

    @Override
    public void startAct(Context context, String serviceType, Bundle bundle) {
        try {
            Activity activity = (Activity) context;
            if (ActivityTag.TESTACTICITY == ActivityTag.searchValue(serviceType)) {
                Utils.startAct(TestActivity.class);
            }
        } catch (Exception e) {
            Logger.e(TAG, e.toString());
        }
    }

}



