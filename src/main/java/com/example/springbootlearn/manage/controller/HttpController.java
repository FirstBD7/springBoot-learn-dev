package com.example.springbootlearn.manage.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.springbootlearn.manage.model.User;
import com.example.springbootlearn.manage.vo.Result;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
            HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/test/test01", HttpMethod.POST, entity, String.class);
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

    @GetMapping("httpGetTest")
    public Result httpGetTest()
    {
        return Result.ok();
    }

    @PostMapping("/httpServletRequestTest")
    public Result httpServletRequestTest(HttpServletRequest httpServletRequest)
    {
        logger.info("httpServletRequestTest：{}", httpServletRequest.getServletContext());
        return Result.ok();
    }

    @PostMapping("/httpServletResponseTest")
    public Result httpServletResponseTest(HttpServletResponse httpServletResponse)
    {
        logger.info("httpServletResponseTest：{}", httpServletResponse.getHeader("Content-Type"));
        return Result.ok();
    }

    @GetMapping("/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 1. 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("数据报表");

        // 2. 填充数据
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("姓名");
        headerRow.createCell(1).setCellValue("年龄");
        headerRow.createCell(2).setCellValue("部门");

        // 示例数据
        List<User> users = getUserList();
        for (int i = 0; i < users.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(users.get(i).getName());
            row.createCell(1).setCellValue(users.get(i).getAge());
            row.createCell(2).setCellValue(users.get(i).getDepartment());
        }

        // 3. 设置响应头
        String fileName = "用户数据_" + LocalDate.now() + ".xlsx";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + fileName + "\"");

        // 4. 写入响应流
        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            outputStream.flush();
        } finally {
            workbook.close();
        }
    }

    private List<User> getUserList() {
        // 返回用户列表
        return Arrays.asList(
                new User("张三", 25, "技术部"),
                new User("李四", 30, "销售部"),
                new User("王五", 28, "市场部")
        );
    }
}
