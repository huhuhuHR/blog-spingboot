package com.huorong.dao;

import com.huorong.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huorong on 17/9/29.
 */
public interface ArticleDao {
    int checkCookieExist(@Param("cookie") String cookie);

    List<Article> selectArticleList(@Param("id") String id);
}
