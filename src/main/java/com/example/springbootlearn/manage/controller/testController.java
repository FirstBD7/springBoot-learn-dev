package com.example.springbootlearn.manage.controller;


import com.alibaba.fastjson2.JSONObject;
import com.example.springbootlearn.manage.vo.Result;
import com.example.springbootlearn.manage.vo.test01Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class testController {
    private static final Logger logger = LoggerFactory.getLogger(testController.class);


    @PostMapping("/test01")
    public Result test01(@RequestBody test01Vo vo)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","张三");
        jsonObject.put("age",18);
        jsonObject.put("sex","男");
        jsonObject.put("address","上海");
        jsonObject.put("phone","12345678901");
        logger.info("jsonObject：{}", jsonObject.toString());
        logger.info("vo：{}", vo.toString());
        return Result.ok();
    }
}
