package com.test.http;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author zhuling
 * @create 2021-05-13 17:46:51
 * @desc 调用http请求接口工具类
 **/
@Slf4j
public class HttpUtil {

    /**
     * 带Authorization请求头的GET请求
     * @param url
     * @param address
     * @param params
     * @return
     */
    public static String doGet(String url, String address, String token, Map<String, Object> params, boolean isJson){
        HttpParamers paramers = null;
        HttpService httpService = null;
        HttpHeader header = null;
        String response = "";
        try {
            log.info("开始调用{}{}接口", url,address);
            paramers = HttpParamers.httpGetParamers();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                paramers.addParam(param.getKey(), param.getValue());
            }
            if (isJson) {
                paramers.setJsonParamer();
            }
            header = new HttpHeader();
            header.addParam("Authorization",token);
            httpService = new HttpService(url);
            log.info("请求头参数{}",header.getParams());
            log.info("请求体参数{}",paramers.toString());
            response = httpService.service(address, paramers, header, isJson);
            log.info("结束调用{}{}接口,结果：{}",url,address, response);
        }catch (Exception e){
            log.info("",e);
        }
        return response;
    }

    /**
     * 带Authorization请求头的post请求
     * @param url
     * @param address
     * @param token
     * @return
     */
    public static String doPost(String url,String address, String token, Map<String, Object> params, boolean isJson){
        HttpParamers paramers = null;
        HttpService httpService = null;
        HttpHeader header = null;
        String response = "";
        try {
            paramers = HttpParamers.httpPostParamers();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                paramers.addParam(param.getKey(), param.getValue());
            }
            header = new HttpHeader();
            if (token != null) {
                header.addParam("Authorization", token);
            }
            if (isJson) {
                paramers.setJsonParamer();
                header.addParam("Content-Type", "application/json;charset=UTF-8");
            } else {
                header.addParam("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            }

            httpService = new HttpService(url);
            response = httpService.service(address, paramers, header, isJson);
        }catch (Exception e){
            log.info("",e);
        }
        return response;
    }
}
