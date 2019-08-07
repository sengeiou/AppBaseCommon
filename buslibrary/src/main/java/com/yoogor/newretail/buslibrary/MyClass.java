package com.yoogor.newretail.buslibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClass {

    public static void main(String[] args) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.yoogor.newretail.buslibrary.TestInvoke");

            Object o = aClass.getConstructor().newInstance();

            Method method = aClass.getDeclaredMethod("setName",String.class);
            method.invoke(o,"alkdj");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}


