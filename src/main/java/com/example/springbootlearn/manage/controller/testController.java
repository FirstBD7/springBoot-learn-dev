package com.example.springbootlearn.manage.controller;


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
        logger.info("vo{}", vo);
        return Result.ok();
    }
}
