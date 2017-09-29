package com.huorong.service;

import com.huorong.dao.ArticleDao;
import com.huorong.domain.Article;
import com.huorong.utils.MyMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huorong on 17/9/29.
 */
@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    public boolean checkCookieRecord(String cookie) {
        return articleDao.checkCookieExist(MyMapUtils.of("cookie", cookie)) == 1;
    }

    public List<Article> selectArticleList(String id) {
        return articleDao.selectArticleList(MyMapUtils.of("id", id));
    }
}
