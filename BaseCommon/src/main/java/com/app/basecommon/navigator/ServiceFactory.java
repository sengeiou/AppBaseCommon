package com.app.basecommon.navigator;

import android.content.Context;
import android.os.Bundle;

import com.app.basecommon.R;
import com.app.basecommon.utiles.Utils;

import java.util.HashMap;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 14:59
 * @Describe
 */
public class ServiceFactory implements IModuleService{

    private static final ServiceFactory ourInstance = new ServiceFactory();

    private HashMap<String,IModuleService> mService;

    private ServiceFactory() {
        mService = new HashMap<>();
    }

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    @Override
    public void register(String serviceType,IModuleService moduleService) {
        mService.put(serviceType,moduleService);
    }

    @Override
    public void startAct(Context context, String serviceType, Bundle bundle) {
        if (mService.isEmpty()){
            return;
        }
        if (mService.containsKey(serviceType)){
            IModuleService iModuleService = mService.get(serviceType);
            iModuleService.startAct(context,serviceType,bundle);
        }else {
            Utils.toast(R.string.navigate_unregisted);
        }
    }

}
