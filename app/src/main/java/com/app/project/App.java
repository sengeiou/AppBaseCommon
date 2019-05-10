package com.app.project;

import com.app.basecommon.base.BaseApp;
import com.app.basecommon.navigator.ServiceFactory;
import com.app.testlib.ActivityTag;
import com.app.testlib.TestModuleServiceimpl;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 16:58
 * @Describe
 */
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceFactory.getInstance().register(ActivityTag.TESTACTICITY.getTag(),new TestModuleServiceimpl());
    }


}
