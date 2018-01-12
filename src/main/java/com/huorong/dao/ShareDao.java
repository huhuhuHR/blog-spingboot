package com.huorong.dao;

import com.huorong.domain.BlogShare;

import java.util.List;
import java.util.Map;

public interface ShareDao {
    int insertShare(Map prams);

    List<BlogShare> selectNewestShare(Map params);
}
