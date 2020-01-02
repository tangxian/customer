package com.boot.OkHttp3;

import com.boot.BootApplication;
import com.boot.core.common.constant.factory.ConstantFactory;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@WebAppConfiguration
public class ConnectTest {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private RestTemplate restTemplate=new RestTemplate( new OkHttp3ClientHttpRequestFactory());
    @Test
    public void testLogin() {
        try {
            String url = "http://api.mpen.com.cn/v1/user/login";
            // 初始化 OkHttpClient
            OkHttpClient client = new OkHttpClient();
            boolean b = client.followSslRedirects();
            HashMap<String, String> map = new HashMap<>();
            map.put("userName", "zou2");
            //map.put("verifyCode", "619669");
            map.put("password", "123");
            map.put("type", "loginId");
            map.put("appType", "DI_RUI_CLOUD");
            //请求参数体
            RequestBody requestBody =  RequestBody.create(JSON, com.alibaba.fastjson.JSON.toJSONString(map));
            /*RequestBody requestBody = new FormBody.Builder()
                    .add("userName", "18832089420")
                    .add("password", "96e79218965eb72c92a549dd5a330112")
                    .add("type", "mobile")
                    .add("appType", "DI_RUI_YUN")
                    .build();*/

            // 初始化请求体
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            // 得到返回Response
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            Map map1 = JSONObject.parseObject(s, Map.class);

            System.out.println(map1);
            String message = response.message();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testRegister(){
        try {
            String url = "http://127.0.0.1:8080/aa";
            // 初始化 OkHttpClient
            OkHttpClient client = new OkHttpClient();
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "22");
           /* map.put("password", "12");
            map.put("type", "loginId");
            map.put("appType", "DI_RUI_YUN");*/
            //请求参数体
            RequestBody requestBody =  RequestBody.create(JSON, map.toString());
            /*RequestBody requestBody = new FormBody.Builder()
                    .add("userName", "18832089420")
                    .add("password", "96e79218965eb72c92a549dd5a330112")
                    .add("type", "mobile")
                    .add("appType", "DI_RUI_YUN")
                    .build();*/

            // 初始化请求体
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            // 得到返回Response
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            Map map1 = JSONObject.parseObject(s, Map.class);

            System.out.println(map1);
            String message = response.message();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testLog(){
        String s = ConstantFactory.me().setUsercenterToken("aa", "aa "+"1");
        String aa = ConstantFactory.me().getUsercenterToken("aa");
        System.out.println("s    "+s);
        System.out.println("aa    "+aa);
    }
}
