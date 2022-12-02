package com.test.utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/** 
* @author liangyadong 
* @date 2016年10月13日 下午3:23:22 
* @version 1.0 
*/
public class JdbcUtils {
    public static String test;
    public static final String DRIVERCLASS;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        System.out.println(connection.getClientInfo());
    }

    /**
     * 静态代码块
     * 
     * 步骤
     * 1.创建properties对象
     * 2.获取db配置文件的输入流
     * 3.properties对象加载配置文件
     * 4.properties对象通过key获取值
     */
    static{
        // 读取配置文件,将值赋给常量
        // 创建properties对象
        Properties pro = new Properties();
        
        // 获取db.properties文件的输入流
        InputStream in = null;
        try {
            in = new FileInputStream(new File("src/main/resources/db.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 加载配置文件
        try {
            pro.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // 通过key获取值
        DRIVERCLASS = pro.getProperty("driverClass");
        URL = pro.getProperty("url");
        USERNAME = pro.getProperty("username");
        PASSWORD = pro.getProperty("password");
        
    }
    
    /**
     * 加载驱动
     */
    public static void loadDriver(){
        try {
            Class.forName(DRIVERCLASS);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        // TODO Auto-generated method stub
        // 加载驱动
        loadDriver();
        try {
            // 获取连接
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 释放资源
     * 
     * 查询的释放资源方法
     * @param conn
     * @param sta
     * @param rs
     */
    public static void release(Connection conn,Statement sta,ResultSet rs){
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rs = null;
        }
        if (sta!=null) {
            try {
                sta.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sta = null;
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            conn = null;
        }
    }
    
    /**
     * 释放资源
     * 
     * 增删改的释放资源方法
     * @param conn
     * @param sta
     */
    public static void release(Connection conn,Statement sta){
        if (sta!=null) {
            try {
                sta.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sta = null;
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            conn = null;
        }
    }
    

}