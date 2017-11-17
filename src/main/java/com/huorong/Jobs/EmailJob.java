package com.huorong.Jobs;

import com.alibaba.fastjson.JSON;
import com.huorong.service.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.huorong.utils.EmailUtil.sendEmail;
import static com.huorong.utils.HttpUtils.sendGet;

/**
 * Created by beck on 2017/11/15.
 */
@Component
public class EmailJob {
    private static final Logger log = LoggerFactory.getLogger(EmailJob.class);
    private final static long ONE_Minute = 60 * 1000;


    @Scheduled(fixedRate = ONE_Minute)
    public void fixedRateJob() {
        RedisService redisService = new RedisService();
        String url = "https://gupiao.jd.com/package/historyConverts?callback=jQuery18301490232655384114_1510649363890&pin=%E8%8A%B3%E8%8D%89101&pageNum=1&_=1510649503271";
        String s = sendGet(url, "");
        String result = s.substring(s.indexOf("{"), s.lastIndexOf(")"));
        System.out.println(result);
        Map map = JSON.parseObject(result, Map.class);
        String oldStockNum = redisService.getStr("stockNum");
        if (oldStockNum != map.get("pageCount").toString()) {
            sendEmail();
            redisService.setStr("stockNum", map.get("pageCount").toString());
        }
        log.debug(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + ">>fixedRate执行....");
    }

}


