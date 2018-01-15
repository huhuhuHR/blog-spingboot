package com.huorong.service;

import com.huorong.dao.ShareDao;
import com.huorong.domain.BlogShare;
import com.huorong.utils.MapUtils;
import org.n3r.idworker.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ShareService {
    private Logger log = LoggerFactory.getLogger(ShareService.class);
    @Autowired
    ShareDao shareDao;
    private final long ONE_DAY = 86400000;
    private final long ONE_HOUR = 3600000;

    public boolean insertShare(Map map) {
        prepareParams(map);
        try {
            return shareDao.insertShare(map) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("分享落值失败");
            return false;
        }
    }

    public void prepareParams(Map map) {
        map.put("shareId", Id.next());
        map.put("recordCount", "0");
    }

    public List<BlogShare> selectNewestShare(Map params) {
        String type = MapUtils.getStr(params, "type");
        if ("1".equals(type)) {
            params = new HashMap();
        }
        List<BlogShare> shares = shareDao.selectNewestShare(params);
        shares.stream().forEach((BlogShare blogShare) -> {
            String createTime = blogShare.getCreateTime();
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
                long mills = calendar.getTimeInMillis();
                long nowMills = System.currentTimeMillis();
                long sub = nowMills - mills;
                if (sub >= ONE_DAY) {
                    int days = 0;
                    days = (int) (sub / ONE_DAY);
                    blogShare.setDayString(String.valueOf(days) + "天前");
                } else {
                    int hours = 0;
                    if (sub < ONE_HOUR) {
                        blogShare.setDayString("刚刚");
                    } else {
                        hours = (int) (sub / ONE_HOUR);
                        blogShare.setDayString(String.valueOf(hours) + "小时前");
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return shares;
    }

    public List<Map> selectMyShare(String userId) {
        return shareDao.selectMyShare(userId);
    }

    public boolean deleteMyShare(String userId, String shareId) {
        return shareDao.deleteMyShare(userId, shareId) == 1;
    }

    public void updateCountByShareId(String userId) {
        shareDao.updateCountByShareId(userId);
    }
}
