package com.stardust.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class _Field {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.stardust.reflection.TargetClass");
        System.out.println("-----all public fields----");
        Arrays.stream(clazz.getFields()).forEach(f -> System.out.println(f));
        System.out.println("-----all private/protected/default fields----");
        Arrays.stream(clazz.getDeclaredMethods()).forEach(f -> System.out.println(f));
        System.out.println("-----get static field value----");
        Field aliasPrefix = clazz.getDeclaredField("aliasPrefix");
        aliasPrefix.setAccessible(true);
        System.out.println(aliasPrefix.get(clazz));
        System.out.println("-----set&get instance field value----");
        Object target = clazz.getConstructor(int.class).newInstance(10);
        Field calls = clazz.getField("calls");
        System.out.println("initialCalls=" + calls.get(target));
        calls.set(target, 20);
        System.out.println("after set calls=" + calls.get(target));
    }
}
