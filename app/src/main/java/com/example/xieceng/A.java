package com.example.xieceng;

import java.lang.reflect.Method;

public class A {


    private void get() throws Exception {
        A a = new A();
        Class aa = Class.forName("A");
        Class c = a.getClass();
        Method[] methods = c.getDeclaredMethods();
        c.getMethods();
        c.getDeclaredMethods();
        c.getAnnotations();
        c.getDeclaredAnnotations();
        c.getComponentType();
        c.getConstructors();
        c.getDeclaredConstructors();
        c.newInstance();
        c.isInstance(a);//方法等同于instanceof
        c.getTypeParameters();
        c.getFields();// public 属性，包括超类的，数组，基本数据类型，void返回0
        c.getDeclaredFields();//包括出inherited的所有，数组，基本数据类型，void返回0
        c.getEnclosingClass();

    }
    /**
     *getMethods() 获取public 的方法
     * getDeclaredMetods 获取所有的方法
     *
     *
     *
     *
     *
     */
}
