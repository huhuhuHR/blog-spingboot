package com.huorong.service;

import com.huorong.dao.ShareDao;
import com.huorong.domain.BlogShare;
import com.huorong.utils.MapUtils;
import org.n3r.idworker.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShareService {
    private Logger log = LoggerFactory.getLogger(ShareService.class);
    @Autowired
    ShareDao shareDao;

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
        return shareDao.selectNewestShare(params);
    }
}
