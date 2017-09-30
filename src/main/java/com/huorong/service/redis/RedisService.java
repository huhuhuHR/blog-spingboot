package com.huorong.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by huorong on 17/9/30.
 */
@Service
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;

    /**
     * 根据指定key获取String
     * 
     * @param key
     * @return
     */
    public String getStr(String key) {
        return valOpsStr.get(key);
    }

    /**
     * 设置Str缓存
     * 
     * @param key
     * @param val
     */
    public void setStr(String key, String val) {
        valOpsStr.set(key, val);
    }

    /**
     *
     * @param key
     * @param val
     * @param expre
     *            h
     */
    public void setStrExpreDay(String key, String val, long expre) {
        valOpsStr.set(key, val, expre, TimeUnit.DAYS);
    }

    /**
     * 
     * @param key
     * @param val
     * @param expre
     *            h
     */
    public void setStrExpreHour(String key, String val, long expre) {
        valOpsStr.set(key, val, expre, TimeUnit.HOURS);
    }

    /**
     *
     * @param key
     * @param val
     * @param expre
     *            m
     */
    public void setStrExpreMinute(String key, String val, long expre) {
        valOpsStr.set(key, val, expre, TimeUnit.MINUTES);
    }

    /**
     *
     * @param key
     * @param val
     * @param expre
     *            s
     */
    public void setStrExpreSecond(String key, String val, long expre) {
        valOpsStr.set(key, val, expre, TimeUnit.SECONDS);
    }

    /**
     *
     * @param key
     * @param val
     * @param expre
     *            ms
     */
    public void setStrExpreMS(String key, String val, long expre) {
        valOpsStr.set(key, val, expre, TimeUnit.MILLISECONDS);
    }

    /**
     * 删除指定key
     * 
     * @param key
     */
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 根据指定o获取Object
     * 
     * @param o
     * @return
     */
    public Object getObj(Object o) {
        return valOpsObj.get(o);
    }

    /**
     * 设置obj缓存
     * 
     * @param o1
     * @param o2
     */
    public void setObj(Object o1, Object o2) {
        valOpsObj.set(o1, o2);
    }

    /**
     * 删除Obj缓存
     * 
     * @param o
     */
    public void delObj(Object o) {
        redisTemplate.delete(o);
    }

}