package com.qa.restclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class RestClient {
    public Logger Log = Logger.getLogger(String.valueOf(RestClient.class));

    /**
     * 不带请求头的get方法封装
     * @param url
     * @return 返回响应对象
     * @throws ClientProtocolException
     * @throws IOException
     */
    public CloseableHttpResponse get (String url) throws ClientProtocolException, IOException {

        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建一个HttpGet的请求对象
        HttpGet httpget = new HttpGet(url);
        //执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
        Log.info("开始发送POST请求...");
        CloseableHttpResponse httpResponse = httpclient.execute(httpget);
        Log.info("发送请求成功！开始得到响应对象。");
        return httpResponse;
    }

    public CloseableHttpResponse post(String url, List<NameValuePair> params, HashMap<String,String> headermap) throws ClientProtocolException, IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Log.info("开始发送POST请求...");
        //创建一个HttpPost的请求对象
        HttpPost httppost = new HttpPost(url);
        //设置payload
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//        httppost.setEntity(new StringEntity(entityString));
        //加载请求头到httppost对象
        for(Map.Entry<String, String> entry : headermap.entrySet()) {
            httppost.addHeader(entry.getKey(), entry.getValue());
        }
        //发送post请求
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);

        Log.info("发送请求成功！开始得到响应对象。");
        Log.info("测试响应状态码是否是200");
        Log.info("接口内容响应断言");
        Log.info("用例执行结束...");
        return httpResponse;
    }
    public CloseableHttpResponse sendpost(String url, String entityString, HashMap<String,String> headermap) throws ClientProtocolException, IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Log.info("开始发送POST请求...");
        //创建一个HttpPost的请求对象
        HttpPost httppost = new HttpPost(url);
        //设置payload
        httppost.setEntity(new StringEntity(entityString));
        //加载请求头到httppost对象
        for(Map.Entry<String, String> entry : headermap.entrySet()) {
            httppost.addHeader(entry.getKey(), entry.getValue());
        }
        //发送post请求
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);

        return httpResponse;
    }
    public CloseableHttpResponse post(String url) throws ClientProtocolException, IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Log.info("开始发送POST请求...");
        //创建一个HttpPost的请求对象
        HttpPost httppost = new HttpPost(url);

        //发送post请求
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);
        Log.info("发送请求成功！开始得到响应对象。");
        Log.info("测试响应状态码是否是200");
        Log.info("接口内容响应断言");
        Log.info("用例执行结束...");
        return httpResponse;
    }
    public CloseableHttpResponse jsonpost(String url,String entityString) throws ClientProtocolException, IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Log.info("开始发送POST请求...");
        //创建一个HttpPost的请求对象
        HttpPost httppost = new HttpPost(url);

        httppost.setEntity(new StringEntity(entityString));
        //发送post请求
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);
        Log.info("发送请求成功！开始得到响应对象。");
        Log.info("测试响应状态码是否是200");
        Log.info("接口内容响应断言");
        Log.info("用例执行结束...");
        return httpResponse;
    }

}
