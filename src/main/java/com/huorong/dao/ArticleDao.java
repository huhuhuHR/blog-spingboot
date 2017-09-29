package com.huorong.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/9/29.
 */
public interface ArticleDao {
    int checkCookieExist(Map parms);

    List<Map> selectArticleList(Map parms);
}
