package com.huorong.dao;

import com.huorong.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by huorong on 17/7/16.
 */
public interface LoginDao {
    User findUserByName(@Param("name") String name);

    int insertCokie(Map params);
}
