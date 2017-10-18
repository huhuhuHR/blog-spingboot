package com.huorong.dao;

import com.huorong.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/9/29.
 */
public interface ArticleDao {
    int checkCookieExist(@Param("cookie") String cookie);

    List<Article> selectArticleList(@Param("id") String id);

    List<Article> searchByValue(Map params);

    int saveArticle(Map params);

    Article articleDetail(String id);

    int articleDelete(String id);

    int updateArticle(Map params);
}
