package com.huorong.service.impl;

import com.huorong.dao.LoginDao;
import com.huorong.domain.User;
import com.huorong.service.LoginService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by huorong on 17/7/16.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    LoginDao loginDao;

    @Override
    public Boolean checkUser(Map params) {
        User user = loginDao.findUser(params);
        try {
            if (MapUtils.getString(params, "password").equals(user.getPassword())) {
                return true;
            }
        } catch (Exception e) {
            log.debug("查到的为空");
        }
        return false;
    }
}
