package com.test.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isBlank(String str) {
        return !isNotBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return org.springframework.util.StringUtils.hasText(str);
    }

    public static String null2Blank(String s) {
        return isNotBlank(s) ? s : "";
    }

    public static String clearString(String s) {
        if (s != null) {
            return s.replaceAll("\\t", "").replaceAll("\\n", "").replaceAll(" ", "");
        } else {
            return null;
        }
    }

    /**
     * 替换字符串的空白，空格，制表符
     * 
     * @param str
     *            需要替换的字符串
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("").trim();
        }
        return dest;
    }

    /**
     * ip校验
     * 
     * @param ip
     * @return Boolean
     */
    public static Boolean isIpAddress(String ip) {
        String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ip);
        return m.matches();
    }

    /**
     * 把秒转换为分钟、小时、天
     * 
     * @param statusMinute
     * @return
     */
    public static String timeStamp(Integer statusMinute) {
        if (statusMinute == null) {
            return null;
        }
        int day = statusMinute / 60 / 60 / 24;
        int hour = statusMinute / 60 / 60 % 24;
        int min = statusMinute / 60 % 60;
        String result = "";
        if (day > 0) {
            result = day + "天";
        }
        if (hour > 0) {
            result += hour + "小时";
        }
        if (min > 0) {
            result += min + "分钟";
        }
        return result;
    }
}