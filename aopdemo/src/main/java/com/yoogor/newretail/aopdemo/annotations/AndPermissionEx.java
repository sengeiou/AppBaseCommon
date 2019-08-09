package com.yoogor.newretail.aopdemo.annotations;

import com.yoogor.newretail.aopdemo.enums.PermissionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/8 17:50
 * @Describe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AndPermissionEx {

    PermissionType type() default PermissionType.RUNTIME;

    String[] permissions() default "";
}
