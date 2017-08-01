package com.hy.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by huangyong on 2017/8/1
 * 一些通道或者流的通用关闭方法
 */
public class CloseUtil {
    public static void close(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
