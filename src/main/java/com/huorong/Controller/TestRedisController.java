package com.huorong.Controller;

import com.huorong.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huorong on 17/9/30.
 */
@RestController
@RequestMapping("redis")
public class TestRedisController {
    @Autowired
    RedisService redisService;

    @RequestMapping("/add")
    public String addKey() {
        redisService.setStrExpreSecond("huorong", "12345", 30);
        return "ok";
    }

    @RequestMapping("/get")
    public String getKey() {
        return redisService.getStr("huorong");
    }

    @RequestMapping("/delete")
    public String deleteKey() {
        redisService.del("huorong");
        return "ok";
    }
}
