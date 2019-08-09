package com.yoogor.newretail.aopdemo;

import android.app.Activity;
import android.util.Log;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.option.Option;
import com.yoogor.newretail.aopdemo.annotations.AfterPermission;
import com.yoogor.newretail.aopdemo.annotations.AndPermissionEx;
import com.yoogor.newretail.aopdemo.enums.PermissionType;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/8 18:02
 * @Describe
 */
@Aspect
public class CheckPermission {

    private final static String TAG = "CheckPermission";

    @Pointcut("execution(@com.yoogor.newretail.aopdemo.annotations.AndPermissionEx * *(..))")
    public void checkPermission() {
    }

    @Around("checkPermission()")
    public void doCheck(ProceedingJoinPoint point) {
        Log.e(TAG, "-doCheck");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AndPermissionEx annotation = method.getAnnotation(AndPermissionEx.class);
        if (annotation == null) return;
        PermissionType type = annotation.type();
        String[] permissions = annotation.permissions();
        startCheckPermission(point, type, permissions);
    }

    private void getAnnotation(JoinPoint point, Class cla, Throwable throwable) {
        if (point == null) return;

        MethodSignature signature = (MethodSignature) point.getSignature();
        Class declaringType = signature.getDeclaringType();

        try {

            Object object = declaringType.newInstance();
            Method[] declaredMethods = declaringType.getDeclaredMethods();

            for (Method declaredMethod : declaredMethods) {
                declaredMethod.setAccessible(true);
                Annotation annotation = declaredMethod.getAnnotation(cla);
                if (annotation != null) {
                    if (throwable != null) {
                        declaredMethod.invoke(object, throwable);
                    } else {
                        declaredMethod.invoke(object);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void startCheckPermission(final ProceedingJoinPoint point, PermissionType type, String... permissions) {
        Option with = AndPermission.with((Activity) point.getThis());
        if (type == PermissionType.RUNTIME) {
            with.runtime()
                    .permission(permissions)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            try {
                                point.proceed();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            try {
                                point.proceed();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    }).start();
        } else if (type == PermissionType.INSTALL) {
            with.install();
        } else if (type == PermissionType.OVERLAY) {
        }

    }

}
