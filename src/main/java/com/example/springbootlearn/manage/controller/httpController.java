package com.example.springbootlearn.manage.controller;


import com.alibaba.fastjson2.JSONObject;
import com.example.springbootlearn.manage.vo.Result;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/httpTo")
public class httpController {
    private static final Logger logger = LoggerFactory.getLogger(httpController.class);

    @GetMapping("test01")
    public Result test01()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","张三");
        jsonObject.put("age",18);
        jsonObject.put("sex","男");
        jsonObject.put("address","上海");
        jsonObject.put("phone","12345678901");

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/test/test01");
            httpPost.setEntity(new StringEntity(jsonObject.toString()));
            logger.info("请求内容：{}", httpPost.toString());
            httpClient.execute(httpPost, response ->{
                logger.info("响应状态：{}", response.getCode());
                logger.info("响应内容：{}", response.getEntity().getContent().toString());
                return null;
            });
        }
        catch(Exception e) {
            logger.info("异常：{}", e.getMessage());
        }
        return Result.ok();
    }
}
