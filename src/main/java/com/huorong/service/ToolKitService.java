package com.huorong.service;

import com.huorong.dao.ToolKitDao;
import com.huorong.domain.Toolkit;
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
}
