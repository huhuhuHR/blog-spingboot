package com.huorong.service;

import com.huorong.domain.User;

import java.util.Map;

/**
 * Created by huorong on 17/7/16.
 */
public interface LoginService {
    Map checkUser(String name, String password);

    User findUser(String name);

    int insertCokie(Map params);
}
