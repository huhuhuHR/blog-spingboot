package com.huorong.service;

import com.huorong.dao.ArticleDao;
import com.huorong.utils.MyMapUtils;
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
        return articleDao.checkCookieExist(MyMapUtils.of("cookie", cookie)) == 1;
    }

    public List<Map> selectArticleList(String id) {
        return articleDao.selectArticleList(MyMapUtils.of("id", id));
    }
}
