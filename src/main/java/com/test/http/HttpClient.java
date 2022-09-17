package com.test.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpClient {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String JSON_CONTENT_FORM = "application/json;charset=UTF-8";
    public static final String CONTENT_FORM = "application/x-www-form-urlencoded;charset=UTF-8";

    public static String doService(String url, HttpParamers paramers, HttpHeader header, int connectTimeout, int readTimeout, boolean isJson) throws Exception {
        HttpMethod httpMethod = paramers.getHttpMethod();
        switch (httpMethod) {
            case GET:
                if (!isJson)
                    return doGet(url, paramers, header, connectTimeout, readTimeout);
                else
                    return sendJsonByGetReq(url, paramers, header);
            case POST:
                return doPost(url, paramers, header, connectTimeout, readTimeout);
        }
        return null;
    }

    /**
     * post方法
     * @param url
     * @param paramers
     * @param header
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     */
    public static String doPost(String url, HttpParamers paramers, HttpHeader header, int connectTimeout, int readTimeout) throws IOException  {
        String responseData = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try{
            String query = null;
            HttpPost httpPost = new HttpPost(url);
            setHeader(httpPost, header);
            if (paramers.isJson()) {
                //json数据
                httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_CONTENT_FORM);
                query = paramers.getJsonParamer();
            } else {
                //表单数据
                httpPost.setHeader(HTTP.CONTENT_TYPE, CONTENT_FORM);
                query = paramers.getQueryString(DEFAULT_CHARSET);
            }
            if(query != null){
                HttpEntity reqEntity = new StringEntity(query, ContentType.create("text/json", "UTF-8"));
                httpPost.setEntity(reqEntity);
            }
            log.info("开始调用{}接口", url);
            log.info("请求头参数{}",httpPost.getHeaders("Content-type"));
            log.info("请求头参数{}",httpPost.getHeaders("Authorization"));
            log.info("请求体参数{}",EntityUtils.toString(httpPost.getEntity()));
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            HttpEntity resEntity = httpResponse.getEntity();
            responseData = EntityUtils.toString(resEntity);
            log.info("结束调用{}接口,返回结果：{}",url, responseData);
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            httpResponse.close();
            httpClient.close();
        }
        return responseData;
    }


    /**
     * get方法
     * @param url
     * @param params
     * @param header
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     */
    public static String doGet(String url, HttpParamers params, HttpHeader header, int connectTimeout, int readTimeout) throws IOException {
        String responseData = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try{
            String query = params.getQueryString(DEFAULT_CHARSET);
            url = buildGetUrl(url, query);
            HttpGet httpGet = new HttpGet(url);
            setHeader(httpGet, header);
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpGet);
            HttpEntity resEntity = httpResponse.getEntity();
            responseData = EntityUtils.toString(resEntity);
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            httpResponse.close();
            httpClient.close();
        }
        return responseData;
    }

    /**
     * 发送get请求，参数为json
     * @param url
     * @param params 参数
     * @return
     * @throws Exception
     */
    public static String sendJsonByGetReq(String url,HttpParamers params, HttpHeader header){
        try {
            String encoding = "UTF-8";
            String body = "";
            //创建httpclient对象
            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity(new URI(url));
            setHeader(httpGetWithEntity, header);
            String query = params.getJsonParamer();
            HttpEntity httpEntity = new StringEntity(query, ContentType.APPLICATION_JSON);
            httpGetWithEntity.setEntity(httpEntity);
            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(httpGetWithEntity);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, encoding);
            }
            //释放链接
            response.close();
            return body;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private static void setHeader(HttpRequestBase httpRequestBase, HttpHeader header){
        if(header != null){
            Map<String,String> headerMap = header.getParams();
            if (headerMap != null && !headerMap.isEmpty()) {
                Set<Map.Entry<String, String>> entries = headerMap.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    String name = entry.getKey();
                    String value = entry.getValue();
                    httpRequestBase.setHeader(name, value);
                }
            }
        }
    }

    private static String buildGetUrl(String url, String query) throws IOException {
        if (query == null || query.equals("")) {
            return url;
        }
        StringBuilder newUrl = new StringBuilder(url);
        boolean hasQuery = url.contains("?");
        boolean hasPrepend = (url.endsWith("?")) || (url.endsWith("&"));
        if (!hasPrepend) {
            if (hasQuery) {
                newUrl.append("&");
            } else {
                newUrl.append("?");
                hasQuery = true;
            }
        }
        newUrl.append(query);
        hasPrepend = false;
        return newUrl.toString();
    }
}
