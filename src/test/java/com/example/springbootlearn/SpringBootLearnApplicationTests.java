package com.example.springbootlearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringBootLearnApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void redisTest01() {
        redisTemplate.opsForValue().set("key01", "hello redis");
        System.out.println("key01:" + redisTemplate.opsForValue().get("key01"));
        stringRedisTemplate.opsForValue().set("key02", "hello string redis");
        System.out.println("key02:" + stringRedisTemplate.opsForValue().get("key02"));
    }
}
