package com.app.navigator.api.factory;

import android.app.Activity;
import android.os.Bundle;

import com.app.basecommon.utiles.Utils;
import com.app.navigator.api.ModuleServices;
import com.app.navigator.api.R;
import com.app.navigator.api.IServicesExtension;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 14:59
 * @Describe
 */
public class ServicesFactory {
    private static final String SUFFIX = "$$extension";
    private static final ServicesFactory instance = new ServicesFactory();

    private Map<String,ModuleServices> mService;

    private ServicesFactory() {
        mService = new HashMap<>();
    }

    public static ServicesFactory getInstance() {
        return instance;
    }

    public void register() {

        String name = IServicesExtension.class.getName() + SUFFIX;
        try {
            IServicesExtension moduleServices = (IServicesExtension) Class.forName(name).newInstance();
            mService = moduleServices.getModuleServices();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ModuleServices getModuleService(String serviceType) {
        if (!mService.isEmpty() && mService.containsKey(serviceType)){
            return mService.get(serviceType);
        }
        Utils.toast(R.string.navigate_unregisted);
        return null;
    }
}
