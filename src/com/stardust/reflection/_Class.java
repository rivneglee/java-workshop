package com.stardust.reflection;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class _Class implements Serializable {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.stardust.reflection.TargetClass");
        System.out.println("-----super class------");
        System.out.println(clazz.getSuperclass());
        System.out.println("-----implements interfaces------");
        Arrays.stream(clazz.getInterfaces()).forEach(i -> System.out.println(i));
        System.out.println("-----internal classes-------");
        Arrays.stream(clazz.getDeclaredClasses()).forEach(c -> System.out.println(c));
    }
}
