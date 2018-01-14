package com.huorong.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface RegistDao {
    int selectBlogByEmail(String blogEmail);

    int selectBlogByName(String blogName);

    int registBlog(Map params);

    int insertEmailLog(Map params);

    String selectUserId(@Param("uuid") String uuid, @Param("msg") String msg);

    int toActive(String userId);

    Map getLoginInfo(Map params);

    String selectUUidByUserId(String userId);
}
