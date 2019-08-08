package com.zhengjr.andpermissionextend;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/8 18:02
 * @Describe
 */
@Aspect
public class CheckPermission {

    @Pointcut("execution(@com.zhengjr.andpermissionextend.AndPermissionEx * *(..))")
    public void checkPermission() {
    }

    @Around("checkPermission()")
    public Object doCheck(ProceedingJoinPoint point) throws Throwable {

        return null;
    }

}
