package com.stardust.io;

import java.io.*;

public class _ObjectStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(new TargetClass());

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
        TargetClass obj = (TargetClass)inputStream.readObject();
        System.out.println(obj.getM_obj());
    }

    private static class TargetClass implements Serializable {
        private int m_int = 0;
        private String m_str = "";
        private EmbeddedClass m_obj = new EmbeddedClass();

        public int getM_int() {
            return m_int;
        }

        public void setM_int(int m_int) {
            this.m_int = m_int;
        }

        public String getM_str() {
            return m_str;
        }

        public void setM_str(String m_str) {
            this.m_str = m_str;
        }

        public EmbeddedClass getM_obj() {
            return m_obj;
        }

        public void setM_obj(EmbeddedClass m_obj) {
            this.m_obj = m_obj;
        }
    }

    private static class EmbeddedClass implements Serializable {

    }
}
