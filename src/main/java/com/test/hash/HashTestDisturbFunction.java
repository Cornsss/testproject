package com.test.hash;

import com.test.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试有无扰动函数下标分布的区别
 */

public class HashTestDisturbFunction {

    /**
     * 扰动函数下的下标获取
     * @param key
     * @param size
     * @return
     */
    public static int disturbHashIdx(String key, int size) {
        return (size - 1) & (key.hashCode() ^ (key.hashCode() >>> 16));
    }

    /**
     * 无扰动函数的下标获取
     * @param key
     * @param size
     * @return
     */
    public static int hashIdx(String key, int size) {
        return (size - 1) & key.hashCode();
    }

    public static void main(String[] args) throws SQLException {
        // 获取数据
        ArrayList<String> words = new ArrayList<>();
        Connection connection = JdbcUtils.getConnection();
        String sql = "select * from enwords";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            String word = rs.getString("word");
            String translation = rs.getString("translation");
            words.add(word + ":" +translation);
        }
//        for (String list : lists) {
//            System.out.println(list);
//        }
        Map<Integer, Integer> map = new HashMap<>(16);
        for (String word : words) {
            // 使用扰动函数
            int idx = disturbHashIdx(word, 128);
            // 不使用扰动函数
            // int idx = Disturb.hashIdx(word, 128);
            if (map.containsKey(idx)) {
                Integer integer = map.get(idx);
                map.put(idx, ++integer);
            } else {
                map.put(idx, 1);
            }
        }
        System.out.println(map.values());// 获取map集合中所有的value值
    }

}
