package com.stardust.jvm;

import com.stardust.reflection.TargetClass;

import java.io.*;

public class _ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader classLoader = new MyClassLoader();
        Class clazz = classLoader.loadClass("/Users/chengli/cookhouse/MyProjects/java-deep-learn/out/production/java-deep-learn/com/stardust/reflection/TargetClass.class");
        Object targetClass = clazz.newInstance();
        System.out.println(targetClass.getClass().toString());
        System.out.println(targetClass.getClass().getClassLoader());
        // parent also loads targetClass so they are different
        System.out.println(targetClass.getClass().equals(TargetClass.class));
    }

    private static class MyClassLoader extends ClassLoader {
        private byte[] loadClassData(String name) {
                try(final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    final InputStream inputStream = new BufferedInputStream(new FileInputStream(name))
                ) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while((length = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    return outputStream.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] classData = loadClassData(name);
            if (classData == null || classData.length == 0) {
                throw new ClassNotFoundException(name);
            }
            return defineClass("com.stardust.reflection.TargetClass", classData, 0, classData.length);
        }
    }
}
