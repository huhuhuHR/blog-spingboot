package com.huorong.Controller;

import com.huorong.domain.Article;
import com.huorong.domain.Result;
import com.huorong.service.ArticleService;
import com.huorong.service.CommonService;
import org.apache.commons.collections.MapUtils;
import org.n3r.idworker.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/9/29.
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    private Logger log = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;
    @Autowired
    CommonService commonService;

    @RequestMapping("/personRecord")
    public Result checkLogin(@RequestParam Map params) {
        String id = MapUtils.getString(params, "id");
        log.info("霍荣" + id);
        String cookie = commonService.CookieDeAESC(MapUtils.getString(params, "cookie"));
        if (!articleService.checkCookieRecord(cookie)) {
            return Result.build("1", "error");
        }
        List<Article> article = articleService.selectArticleList(id);
        return Result.build("0", "ok", com.huorong.utils.MapUtils.asMap("personRecordList", article));
    }

    @RequestMapping("/saveArticle")
    public Result saveArticle(@RequestParam Map params) {
        params.put("id", Id.next());
        return articleService.saveArticle(params) ? Result.build("0", "ok") : Result.build("1", "error");
    }

    @RequestMapping("/articleDetail")
    public Result articleDetail(@RequestParam String id) {
        Article article = articleService.articleDetail(id);
        return article != null ? Result.build("0", "ok", com.huorong.utils.MapUtils.asMap("article", article))
                : Result.build("1", "error");
    }
}
