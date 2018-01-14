package com.huorong.dao;

import com.huorong.domain.BlogShare;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShareDao {
    int insertShare(Map prams);

    List<BlogShare> selectNewestShare(Map params);

    List<Map> selectMyShare(@Param("userId") String userId);

    int deleteMyShare(@Param("userId") String userId, @Param("shareId") String shareId);
}
