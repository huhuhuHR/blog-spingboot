package com.huorong.dao;

import com.huorong.domain.Toolkit;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/12.
 */
public interface ToolKitDao {
    int insertToolKit(Map params);

    String selectUserId(String cookie);

    List<Toolkit> selectTookies(String userId);

    int updateCountById(Map params);
}
