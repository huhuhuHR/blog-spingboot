package com.huorong.service;

import com.huorong.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/7/18.
 */
@Service
public class MyService {
    @Autowired
    TestDao testDao;

    public List<Map> findAllUser() {
        return testDao.findUser();
    }
}
