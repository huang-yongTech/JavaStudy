package com.hy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyong on 2017/7/20
 * 测试正则表达式
 */
public class TestRegex {

    public static void main(String[] args) {
        String str = "Java now has regular expressions";
        String regex="\\w+";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()){
            System.out.println(matcher.start());
        }
    }
}
