package com.app.navigator.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/14 14:47
 * @Describe
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface NavService {
    String name() default "";
}
