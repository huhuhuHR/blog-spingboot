package com.huorong.dao;

import com.huorong.domain.User;

import java.util.Map;

/**
 * Created by huorong on 17/7/16.
 */
public interface LoginDao {
    User findUser(Map params);
}
