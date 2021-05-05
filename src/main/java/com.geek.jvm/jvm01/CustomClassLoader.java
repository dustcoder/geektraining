package com.geek.jvm.jvm01;

import java.io.*;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = new byte[0];
        try {
            b = loadClassFromFile(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) throws FileNotFoundException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName + ".xlass");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1 ) {
                byteStream.write(255 - nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = byteStream.toByteArray();

        return buffer;
    }
}
