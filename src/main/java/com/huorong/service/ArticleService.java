package com.huorong.service;

import com.huorong.dao.ArticleDao;
import com.huorong.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/9/29.
 */
@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    public boolean checkCookieRecord(String cookie) {
        return articleDao.checkCookieExist(cookie) == 1;
    }

    public List<Article> selectArticleList(String id) {
        return articleDao.selectArticleList(id);
    }

    public List<Article> searchByValue(Map params) {
        return articleDao.searchByValue(params);
    }

    public boolean saveArticle(Map params) {
        int result = articleDao.saveArticle(params);
        if (result == 1) {
            return true;
        }
        return false;
    }

    public Article articleDetail(String id) {
        return articleDao.articleDetail(id);
    }

    public boolean articleDelete(String id) {
        int result = articleDao.articleDelete(id);
        if (result == 1) {
            return true;
        }
        return false;
    }

    public boolean updateArticle(Map params) {
        int result = articleDao.updateArticle(params);
        if (result == 1) {
            return true;
        }
        return false;
    }
}
