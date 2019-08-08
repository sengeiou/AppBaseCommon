package com.yoogor.newretail.aopdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/8 13:49
 * @Describe
 */
@Aspect
public class AspectLoginCheck {


    /**
     * 根据切点 切成什么样子
     */
    @Pointcut("execution(@com.yoogor.newretail.aopdemo.LoginCheck * *(..))")
    public void loginCheck() {
    }

    /**
     * 切成什么样子之后，怎么去处理
     */
    @Around("loginCheck()")
    public Object doCheck(ProceedingJoinPoint point) throws Throwable {

        Log.e("tag", "doCheck");

        MethodSignature signature = (MethodSignature) point.getSignature();
        LoginCheck loginCheck = signature.getMethod().getAnnotation(LoginCheck.class);
        if (loginCheck != null) {
            String value = loginCheck.value();
            int type = loginCheck.type();
            if (type == 1) {
                Log.e("tag", "value:" + value + "---type:" + type);
                return point.proceed();
            } else if (type == 2) {
                Log.e("tag", "value:" + value + "---type:" + type);
                return null;
            } else {
                Log.e("tag", "value:" + value + "---type:" + type);

                point.getArgs()[0] = (Object) 2;
                for (Object arg : point.getArgs()) {
                    Log.e("tag", arg.toString());
                }

//                return point.proceed(new Object[]{1});//可以更改返回值
                return point.proceed(new Object[]{1});
            }

        }
        return point.proceed();
    }

}
