package com.hy.utils;

import org.apache.commons.codec.digest.*;

public class EncryptUtils {

    public static void main(String[] args) {
        /*
       String a1="sujq";
       Md5Crypt c=new Md5Crypt();
       String a2=Md5Crypt.md5Crypt(a1.getBytes());
       System.out.println(a2);
       String a3=Md5Crypt.md5Crypt(a1.getBytes(),a2);
       System.out.println(a3);
       //
       if(a2.compareTo(a3)==0){
           System.out.println("psw is ok.");
       }*/

        //1
        String p1 = "222";
        String toSaveIntoDBPwd = EncryptUtils.getCryptPwd(p1);
        System.out.println("to save pwd:" + toSaveIntoDBPwd);
        //2 save into db.
        //...
        //3 check is pwd is ok.
        //3.1 read inDBPwd
        //...
//        String inDbPwd = toSaveIntoDBPwd;
        String inDbPwd = "$1$wLEH1X1C$lSTvr4O2/7cjePmabK.Yd/";
        String p2 = "222";
        boolean isOk = EncryptUtils.checkIsPwdOk(inDbPwd, p2);
        System.out.println(isOk);

    }

    public static String getCryptPwd(String pwd) {
        return Md5Crypt.md5Crypt(pwd.getBytes());
    }

    public static boolean checkIsPwdOk(String inDBPwd, String pwd) {
        String tmpPwd = Md5Crypt.md5Crypt(pwd.getBytes(), inDBPwd);
        System.out.println("密码：" + tmpPwd);
        return tmpPwd.compareTo(inDBPwd) == 0;
    }
}
