package com.example.springbootlearn.manage.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.springbootlearn.manage.vo.Result;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/httpTo")
public class HttpController {
    private static final Logger logger = LoggerFactory.getLogger(HttpController.class);

    @GetMapping("test01")
    public Result test01()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","张三");
        jsonObject.put("age",18);
        jsonObject.put("sex","男");
        jsonObject.put("address","上海");
        jsonObject.put("phone","12345678901");
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("Accept-Charset", "UTF-8");
            HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<>(jsonObject, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/test/test01", HttpMethod.POST, jsonObjectHttpEntity, String.class);
            logger.info("响应状态：{}", response.getStatusCode());
            logger.info("响应内容：{}", response.getBody());
            JSONObject repJson = JSON.parseObject(response.getBody());
            logger.info("repJson-code：{}", repJson.get("code"));
            logger.info("codeIndex:{}", response.getBody().toString().indexOf("code"));
            logger.info("code:{}", response.toString().substring(response.toString().indexOf("code")+6, response.toString().indexOf("code")+7));
        } catch(Exception e) {
            logger.info("异常：{}", e.getMessage());
        }
        return Result.ok();
    }

    @GetMapping("test02")
    public Result test02()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","张三");
        jsonObject.put("age",18);
        jsonObject.put("sex","男");
        jsonObject.put("address","上海");
        jsonObject.put("phone","12345678901");
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost("http://localhost:8080/test/test01");
            httpPost.setEntity(new StringEntity("{\"key\":\"value\"}"));

        } catch(Exception e) {
            logger.info("异常：{}", e.getMessage());
        }
        return Result.ok();
    }
}
