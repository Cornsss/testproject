package com.test.hash;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class HashTest {

    public static void main(String[] args) {
//        // 初始化一组字符串
//        List<String> list = new ArrayList<>();
//        list.add("jlkk");
//        list.add("lopi");
//        list.add("小傅哥");
//        list.add("e4we");
//        list.add("alpo");
//        list.add("yhjk");
//        list.add("plop");
//
//        // 定义要存放的数组
//        String[] tab = new String[8];
//
//        // 循环存放
//        for (String key : list) {
//            int idx = key.hashCode() & (tab.length - 1);  // 计算索引位置
//            System.out.println(String.format("key值=%s Idx=%d", key, idx));
//            if (null == tab[idx]) {
//                tab[idx] = key;
//                continue;
//            }
//            tab[idx] = tab[idx] + "->" + key;
//        }
//        // 输出测试结果
//        System.out.println(JSONUtil.toJsonStr(tab));
        System.out.println(new Object().hashCode());
        System.out.println((new Object().hashCode() >>> 16) & 7);
        System.out.println(7 & new Object().hashCode());
    }

}
