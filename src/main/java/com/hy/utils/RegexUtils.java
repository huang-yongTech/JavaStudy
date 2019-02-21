package com.hy.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyong on 2018/3/21.
 * 正则匹配工具类
 */

public class RegexUtils {
    /**
     * 查找某个html字符串中是否包含img标签
     *
     * @param html  待匹配的字符串
     * @param regex 匹配规则
     * @return 结果
     */
    public static boolean findTagImg(String html, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(html);
        return matcher.find();
    }

    public static List<String> findPContainsImg(String html, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(html);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(html.substring(matcher.start(), matcher.end()));

        }
        return list;
//        int count = matcher.groupCount();
//        if (count > 0) {
//
//            for (int i = 0; i < count; i++) {
//                list.add(matcher.group(count));
//            }
//            return list;
//        } else {
//            return null;
//        }
    }
}
