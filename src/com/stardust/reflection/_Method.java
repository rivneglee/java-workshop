package com.stardust.reflection;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class _Method implements Serializable {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.stardust.reflection.TargetClass");
        System.out.println("-----class public methods-------");
        Arrays.stream(clazz.getMethods()).forEach(method -> System.out.println(method));
        System.out.println("-----class private/default/protected methods-------");
        Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> System.out.println(method));
        System.out.println("-----invoke public instance method---");
        Method getAlias = clazz.getDeclaredMethod("getAlias", String.class);
        System.out.println(getAlias.invoke(clazz.newInstance(),"LC"));
        System.out.println("-----invoke private static  method---");
        Method getAliasPrefix = clazz.getDeclaredMethod("getAliasPrefix");
        getAliasPrefix.setAccessible(true);
        System.out.println(getAliasPrefix.invoke(clazz));
    }
}
