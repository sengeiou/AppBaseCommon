package com.yoogor.newretail.buslibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/7 17:05
 * @Describe
 */
public class EventBus {

    private static EventBus instance;
    // 存储所有订阅者的map
    // map的键就是注册的时候传进去的对象
    private Map<Object, List<MethodManager>> map;

    /**
     * 私有化构造方法
     */
    private EventBus() {
        map = new HashMap<>();
    }
    /**
     * 初始化单例EventBus
     *
     * @return EventBus.this
     */
    public static EventBus getDefault() {
        if (instance == null)
            synchronized (EventBus.class) {
                if (instance == null) instance = new EventBus();
            }
        return instance;
    }

    /**
     * 发布事件
     * @param setter 事件发布的对象类型
     */
    public void post(final Object setter) {
        Set<Object> objects = map.keySet();
        // 循环存储所有订阅者的map
        for (final Object object : objects) {
            // 获取到订阅者所有的订阅者方法的集合
            List<MethodManager> methodManagers = map.get(object);
            if (methodManagers != null && methodManagers.size() > 0) {
                for (final MethodManager methodManager : methodManagers) {
                    // 判断发布者发布事件的发布的对象类型和订阅者方法的定义的类型是否一致
                    if (methodManager.getType().isAssignableFrom(setter.getClass())) {
                        // 通过反射来执行订阅者方法
                        invoke(methodManager, object, setter);
                    }
                }
            }
        }
    }

    /**
     * 通过反射执行订阅者方法
     * @param methodManager 订阅者方法封装对象
     * @param object 订阅者对象（组价）
     * @param setter 订阅者方法定义的参数对象
     */
    private void invoke(MethodManager methodManager, Object object, Object setter) {
        Method method = methodManager.getMethod();
        try {
            // 通过反射执行方法
            method.invoke(object, setter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传进来的组件 找到这个组件中的所有订阅方法
     *
     * @param object
     */
    public void register(Object object) {
        // 获取这个组件所有的订阅者方法集合
        List<MethodManager> methodManagers = map.get(object);
        if (methodManagers == null) {
            // 通过组件对象找到这个组件所有标识了Subscribe注解的方法即为订阅方法
            methodManagers = findAnnotationMethod(object);
            map.put(object, methodManagers);
        }
    }
    /**
     * 通过传进来的组件来找它所有符合条件的订阅方法
     *
     * @param object 组件对象
     * @return 这个组件所有的订阅者方法集合
     */
    private List<MethodManager> findAnnotationMethod(Object object) {
        List<MethodManager> methodList = new ArrayList<>();
        // 获取到组件的类对象
        Class<?> aClass = object.getClass();
        // 通过类对象获取到所有方法的集合
        Method[] declaredMethods = aClass.getDeclaredMethods();
        // 循环方法集合进行判别那个是订阅者方法
        for (Method declaredMethod : declaredMethods) {
            // 返回该方法的注解在此方法的指定注解类型
            Subscribe annotation = declaredMethod.getAnnotation(Subscribe.class);
            if (annotation == null) { // 如果注解为空就表示不是订阅者方法
                continue;
            }
            // 添加严格的校验
            // 获取方法返回值的类型
            Type genericReturnType = declaredMethod.getGenericReturnType();
            if (!genericReturnType.toString().equals("void")) {
                throw new RuntimeException("方法返回值不是void");
            }
            // 获取到方法所有接收参数类型数组
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException("方法必须有且只有一个参数");
            }
            // 生成订阅者方法封装对象
            MethodManager methodManager = new MethodManager(parameterTypes[0], annotation.threadMode(), declaredMethod);
            methodList.add(methodManager);
        }
        return methodList;
    }


}
