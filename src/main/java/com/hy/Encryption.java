package com.hy;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by huangyong on 2017/8/31
 */
public class Encryption {
    @Test
    public void encrypt() {
        System.out.println(new Date().getTime());
    }

    private void encryption(String key) {
        String md5Key = MD5(key);
        if (md5Key != null) {
            int length = md5Key.length();

        }
    }

    private String MD5(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuilder sb = new StringBuilder("");
            for (byte aB : b) {
                i = aB;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            //32位加密
            return sb.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
