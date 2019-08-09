package com.zhengjr.andpermissionextend;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/8 18:02
 * @Describe
 */
@Aspect
public class CheckPermission {

    @Pointcut("execution(@com.zhengjr.andpermissionextend.annotations.AndPermissionEx * *(..))")
    public void checkPermission() {
    }

    @Before("execution(@com.zhengjr.andpermissionextend.annotations.BeforePermission * *(..))")
    public void beforePermission(ProceedingJoinPoint point){
        Log.e("tag","CheckPermission-Click");
    }

    @Around("checkPermission()")
    public Object doCheck(ProceedingJoinPoint point) throws Throwable {
        Log.e("tag","CheckPermission-Click");
        return point.proceed();
    }

    @After("execution(@com.zhengjr.andpermissionextend.annotations.AfterPermission * *(..))")
    public void afterPermission(ProceedingJoinPoint point){
        Log.e("tag","CheckPermission-Click");
    }

    @AfterReturning("execution(@com.zhengjr.andpermissionextend.annotations.ReturningPermission * *(..))")
    public void returningPermission(ProceedingJoinPoint point,Object returnValue){
        Log.e("tag","CheckPermission-Click");
    }

    @AfterThrowing(pointcut = "execution(@com.zhengjr.andpermissionextend.annotations.ThrowingPermission * *(..))", throwing = "ex")
    public Throwable throwingPermission(Throwable ex){
        Log.e("tag","CheckPermission-Click");
        return ex;
    }

}
