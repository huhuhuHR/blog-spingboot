package com.huorong.Jobs;

import com.huorong.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by beck on 2017/11/15.
 */
@Component
public class EmailJob {
    private static final Logger log = LoggerFactory.getLogger(EmailJob.class);
    private final static long ONE_Minute = 60 * 1000;

    // @Scheduled(fixedRate = ONE_Minute)
    // public void fixedRateJob() {
    // Redis redisService = new Redis();
    // String url =
    // "https://gupiao.jd.com/package/historyConverts?callback=jQuery18301490232655384114_1510649363890&pin=%E8%8A%B3%E8%8D%89101&pageNum=1&_=1510649503271";
    // String s = sendGet(url, "");
    // String result = s.substring(s.indexOf("{"), s.lastIndexOf(")"));
    // System.out.println(result);
    // Map map = JSON.parseObject(result, Map.class);
    // String oldStockNum = redisService.get("stockNum");
    // if (!oldStockNum.equals(map.get("pageCount").toString())) {
    // sendEmail();
    // redisService.set("stockNum", map.get("pageCount").toString());
    // }
    // log.debug(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
    // + ">>fixedRate执行....");
    // }
    public static void main(String[] args) {
        String url = "https://gupiao.jd.com/package/historyConverts";
        String callback = "jQuery18301490232655384114_1510649363890";
        String pin = "%E8%8A%B3%E8%8D%89101";
        String pageNum = "1&_=1510649503271";
        Map map = new HashMap();
        map.put("callback", callback);
        map.put("pin", pin);
        map.put("pageNum", pageNum);
        String result = HttpUtils.sendGet(url, prepareMap(map));
        System.out.println(result);
    }

    public static String prepareMap(Map<String, String> map) {
        String result = "";
        for (Map.Entry<String, String> stringIntegerEntry : map.entrySet()) {
            String key = stringIntegerEntry.getKey();
            String value = stringIntegerEntry.getValue();
            result = result.concat(key).concat("=").concat(value).concat("&");
        }
        int index = result.lastIndexOf("&");
        result = result.substring(0, index);
        return result;
    }
}
