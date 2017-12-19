package com.huorong.service.impl;

import com.huorong.dao.LoginDao;
import com.huorong.domain.User;
import com.huorong.service.LoginService;
import com.huorong.utils.MapUtils;
import com.huorong.utils.secret.EDUtils;
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
    public Map checkUser(String name, String password) {
        try {
            String nameE = EDUtils.encrypt(name);
            User user = loginDao.findUserByName(nameE);
            if (password.equals(EDUtils.decrypt(user.getPassword()))) {
                return MapUtils.of("flag", true, "id", user.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("查到的为空");
        }
        return MapUtils.of("flag", false);
    }

    @Override
    public User findUser(String name) {
        return loginDao.findUserByName(name);
    }

    public int insertCokie(Map params) {
        return loginDao.insertCokie(params);
    }
}
