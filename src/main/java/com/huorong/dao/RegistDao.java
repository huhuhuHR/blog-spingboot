package com.huorong.dao;

import java.util.Map;

public interface RegistDao {
    int selectBlogByEmail(String blogEmail);

    int selectBlogByName(String blogName);

    int registBlog(Map params);

    int insertEmailLog(Map params);

    String selectUserId(String uuid);

    int toActive(String userId);
}
