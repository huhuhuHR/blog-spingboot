package com.huorong.service;

import com.huorong.dao.ToolKitDao;
import com.huorong.domain.Toolkit;
import com.huorong.utils.MapUtils;
import com.huorong.utils.RandomAEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/12.
 */
@Service
public class ToolKitService {
    @Autowired
    ToolKitDao toolKitDao;

    public boolean insertToolKit(Map params) {
        return toolKitDao.insertToolKit(params) == 1;
    }

    public String selectUserId(String cookie) {
        return toolKitDao.selectUserId(cookie);
    }

    public List<Toolkit> selectTookies(String userId) {
        return toolKitDao.selectTookies(userId);
    }

    public boolean updateCountById(Map params) {
        return toolKitDao.updateCountById(params) == 1;
    }

    public void checkParmsEmpty(Map params) {
        String iconName = MapUtils.getStr(params, "iconName");
        String urlName = MapUtils.getStr(params, "urlName");
        if (StringUtils.isEmpty(iconName)) {
            params.put("iconName", RandomAEnum.randomAEnum().getIconfont());
        }
        if (StringUtils.isEmpty(urlName)) {
            params.put("urlName", RandomAEnum.randomAEnum().getUrlName());
        }
    }

    public boolean deleteToolkit(Map params) {
        return toolKitDao.deleteToolkit(params) == 1;
    }
}
