package com.qa.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.data.Users;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostApiTest extends TestBase {
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;


    @Test
    public void getRecommendAppList() throws ClientProtocolException, IOException {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/browser/getRecommendAppList";
        Logger Log = LoggerFactory.getLogger(PostApiTest.class);
        Log.info("开始执行用例...");
        restClient = new RestClient();
//        closeableHttpResponse = restClient.get(url);
        //准备请求头信息
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device", "1"));
        params.add(new BasicNameValuePair("chainType", "EOS"));


        closeableHttpResponse = restClient.post(url, params, headermap);

        //验证状态码是不是200
//        Log.info("测试响应状态码是否是200");
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");

        //断言响应json内容中id和token是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        Log.info("执行JSON解析，解析的内容是 " + responseJson);
        Log.info("接口内容响应断言");

        //System.out.println(responseString);
        String message = TestUtil.getValueByJPath(responseJson, "message");
        Assert.assertEquals(message, "Success", "id is not id");

        Log.info("用例执行结束...");

        System.out.println(message);
    }

    @Test
    public void getPopular() throws IOException {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/browser/getPopularSearchWords";
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");

    }

    @Test
    public void getCategoriesAndDApps() throws IOException {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/getCategoriesAndDApps";
        restClient = new RestClient();
        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device", "1"));
        params.add(new BasicNameValuePair("chainType", "EOS"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");


        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }

    @Test
    public void getCoindetail() throws IOException {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/coin/10000449/detail";
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");

    }
    @Test
    public void getProxydetail() throws IOException {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/proxyVote/10000019/detail";
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");

    }
    @Test
    public void getChainlist() throws IOException {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/chain/list";
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }

    @Test
    public void getSearchapp() throws IOException {
       testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/browser/searchApps";


       //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pageSize", "02"));
        params.add(new BasicNameValuePair("pageNum", "1"));
        params.add(new BasicNameValuePair("chainType", "EOS"));
        params.add(new BasicNameValuePair("device", "1"));
        params.add(new BasicNameValuePair("context", "1"));


        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getDAppsByCategory() throws IOException {

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/getDAppsByCategory";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pageSize", "20"));
        params.add(new BasicNameValuePair("pageNum", "1"));
        params.add(new BasicNameValuePair("chainType", "EOS"));
        params.add(new BasicNameValuePair("device", "1"));
        params.add(new BasicNameValuePair("categoryId", "1"));


        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getBannerList() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/getBannerList";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("cate", "RECOMMEND"));
        params.add(new BasicNameValuePair("chainType", "EOS"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");


        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");

    }
    @Test
    public void getRecommendDApps() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/getRecommendDApps";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pageSize", "20"));
        params.add(new BasicNameValuePair("pageNum", "1"));
        params.add(new BasicNameValuePair("device", "1"));
        params.add(new BasicNameValuePair("chainType", "EOS"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void actionUpload() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/actionUpload";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        params.add(new BasicNameValuePair("itemId", "1"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getNewNotify() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/appNotify/getNewNotify";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device", "1"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getNewVersion() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/appVersion/getNewVersion";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("device", "1"));
        params.add(new BasicNameValuePair("majorVersion", "2.4.2"));


        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getAuditResult() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/appVersion/getAuditResult";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("majorVersion", "2.4.2"));


        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getNodes() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/getNodes";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("chainType", "EOS"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getVotelist() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/proxyVote/list";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("chainType", "EOS"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getCoinexchange() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/coin/exchange";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("from", "USD"));
        params.add(new BasicNameValuePair("to", "CNY"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getCoinlist() throws IOException {
        //断言反馈的状态码是否正确

        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/coin/list";


        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("chainType", "EOS"));

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.post(url,params,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getAccount() throws IOException {
        url = "https://api-mainnet.starteos.io/v1/chain/get_account";
        System.out.println(url);
        //对象转换成Json字符串
        Users user = new Users("stproductest");
        String userJsonString = JSON.toJSONString(user);
        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.sendpost(url,userJsonString,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getmine() throws IOException {
        url = "https://geo.eosasia.one/v1/chain/get_account";
        System.out.println(url);
        //对象转换成Json字符串
        Users user = new Users("emememememem");
        String userJsonString = JSON.toJSONString(user);
        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.sendpost(url,userJsonString,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getFriut() throws IOException {
        url = "http://api-mainnet.starteos.io/v1/chain/get_table_rows";

        JSONObject params = new JSONObject();
        params.put("code","games.eos");
        params.put("scope","games.eos");
        params.put("table","gamers");
        params.put("json",true);
        params.put("lower_bound","stproductest");
        params.put("upper_bound","stproductest");


        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

//        StringEntity stringEntity = new StringEntity(params.toJSONString(),"utf-8");
        String stringEntity = JSON.toJSONString(params);
        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.jsonpost(url, stringEntity);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getFriutpark() throws IOException {
        url = "https://geo.eosasia.one/v1/chain/get_currency_balance";

        JSONObject params = new JSONObject();
        params.put("account","games.eos");
        params.put("symbol","EOS");
        params.put("code","eosio.token");

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

//        StringEntity stringEntity = new StringEntity(params.toJSONString(),"utf-8");
        String stringEntity = JSON.toJSONString(params);
        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.jsonpost(url, stringEntity);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getWkaccount() throws IOException {
        url = "https://api.starteos.io/mine/get_user_data";

        JSONObject params = new JSONObject();
        params.put("account","stproductest");

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

//        StringEntity stringEntity = new StringEntity(params.toJSONString(),"utf-8");
        String stringEntity = JSON.toJSONString(params);
        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.sendpost(url, stringEntity,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getWkrex() throws IOException {
        url = "https://api.starteos.io/mine/get_display_data";

        JSONObject params = new JSONObject();
        params.put("account","stproductest");

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

//        StringEntity stringEntity = new StringEntity(params.toJSONString(),"utf-8");
        String stringEntity = JSON.toJSONString(params);
        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.sendpost(url, stringEntity,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getUSDEeos() throws IOException {
        url = "https://proxy.eosnode.tools/v1/chain/get_currency_balance";

        JSONObject params = new JSONObject();
        params.put("code","eosio.token");
        params.put("symbol","EOS");
        params.put("account","stproductest");

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

//        StringEntity stringEntity = new StringEntity(params.toJSONString(),"utf-8");
        String stringEntity = JSON.toJSONString(params);
        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.sendpost(url, stringEntity,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getUSDEcount() throws IOException {
        url = "https://proxy.eosnode.tools/v1/chain/get_currency_balance";

        JSONObject params = new JSONObject();
        params.put("code","usdetotokens");
        params.put("symbol","USDE");
        params.put("account","stproductest");

        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

//        StringEntity stringEntity = new StringEntity(params.toJSONString(),"utf-8");
        String stringEntity = JSON.toJSONString(params);
        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.sendpost(url, stringEntity,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }
    @Test
    public void getEOSCirculation() throws IOException {
        url = "https://geo.eosasia.one/v1/chain/get_currency_stats";

        JSONObject params = new JSONObject();
        params.put("code","eosio.token");
        params.put("symbol","EOS");


        //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/json");

//        StringEntity stringEntity = new StringEntity(params.toJSONString(),"utf-8");
        String stringEntity = JSON.toJSONString(params);
        //传参发送post请求并接收反馈
        restClient = new RestClient();
        closeableHttpResponse = restClient.sendpost(url, stringEntity,hashHead);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
    }

    @Test
    public void getEosPark()throws ClientProtocolException,IOException{
        url = "https://eospark.com/api/v2/overview/high_refresh";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getEosPark1()throws ClientProtocolException,IOException{
        url = "https://eospark.com/api/v2/tokens/price/eos?start=2019-07-17T09:00:00&end=2019-07-18T09:00:00&count=120";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getparkaccount()throws ClientProtocolException,IOException{
        url = "https://eosx-apigw.eosx.io/api/accounts/stproductest";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getDealpark()throws ClientProtocolException,IOException{
        url = "http://stname.starteos.io/serverApi/auction/success?sort=DESC&pageNo=1&pageSize=20";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getDealsearch()throws ClientProtocolException,IOException{
        url = "http://stname.starteos.io/serverApi/auction/search?name=mgm.com";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getBidding()throws ClientProtocolException,IOException{
        url = "http://stname.starteos.io/serverApi/auction/success?order=time&type=ASC&pageNo=0&pageSize=20";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getcirclerank()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/reward/rank?format=json&pageNum=1&pageSize=20";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getcirclecomment()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/article/12043?format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getarticleDetail()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/article/11936/reply?pageNum=1&pageSize=10&format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getarticle()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/article?pageNum=1&pageSize=20&categoryId=0&format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getarticleSearch()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/search/hotkw?format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getarticleSearchxx()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/article/search?pageNum=1&pageSize=20&kw=ff&format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getarticleSearchSelected()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/wxarticle/search?kw=ff&pageNum=1&pageSize=20&format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getarticleSelected()throws ClientProtocolException,IOException{
        url = "https://bbs.hc-o.com/v1/wxarticle?pageNum=1&pageSize=20&format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getQuotation()throws ClientProtocolException,IOException{
        url = "http://market.jinse.com/api/v1/currency/ranks?format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getQMessag()throws ClientProtocolException,IOException{
        url = "https://config.hc-o.com/QMessage/list?page=1&size=100&format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getQSearch()throws ClientProtocolException,IOException{
        url = "http://market.jinse.com/api/v1/klines/BITFINEX:EOSUSD/H1?format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getTransaction()throws ClientProtocolException,IOException{
        url = "https://api.eospark.com/api?module=account&action=get_account_related_trx_info&apikey=9e9a4d239593f9e126ac2c9d58310233&account=stproductest&page=1&size=20&symbol=EOS&code=eosio.token&format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }
    @Test
    public void getProjectdetail()throws ClientProtocolException,IOException{
        url = "https://dapp-center.starteos.io/api/coin/10000403/detail?format=json";
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");
    }

}

