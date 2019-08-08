package com.yoogor.newretail.buslibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClass {

    public static void main(String[] args) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.yoogor.newretail.buslibrary.TestInvoke");

            Object o = aClass.getConstructor().newInstance();


            Field name = aClass.getDeclaredField("num");
            name.setAccessible(true);
            name.setInt(o,4);

            Class<?> type = name.getType();

            Method method = aClass.getDeclaredMethod("getNum");
            Object invoke = method.invoke(o);
            System.out.println(invoke.toString());

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
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}


