package com.yoogor.newretail.annotationdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/5 17:02
 * @Describe
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface IntentKey {
    String value() default "";
}
