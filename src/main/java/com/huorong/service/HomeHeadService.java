package com.huorong.service;

import com.huorong.dao.HomeHeadDao;
import com.huorong.domain.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/9/30.
 */
@Service
public class HomeHeadService {
    @Autowired
    HomeHeadDao homeHeadDao;

    public List<Router> routerList(Map parmas) {
        return homeHeadDao.routerList(parmas);
    }
}
