package com.huorong.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huorong.service.redis.RedisService;

/**
 * Created by huorong on 17/9/30.
 */
@RestController
@RequestMapping("redis")
public class TestRedisController {
    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/add/k={k}&v={v}", method = RequestMethod.GET)
    public String addKey(@PathVariable("k") String key, @PathVariable("v") String value) {
        redisService.setStrExpreSecond(key, value, 30);
        return "ok";
    }

    @RequestMapping(value = "/get/k={k}", method = RequestMethod.GET)
    public String getKey(@PathVariable("k") String key) {
        return redisService.getStr(key);
    }

    @RequestMapping(value = "/delete/k={k}", method = RequestMethod.GET)
    public String deleteKey(@PathVariable("k") String key) {
        redisService.del(key);
        return "ok";
    }
}
