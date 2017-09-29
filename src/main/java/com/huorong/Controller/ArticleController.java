package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.service.ArticleService;
import com.huorong.service.CommonService;
import com.huorong.utils.MyMapUtils;
import org.apache.commons.collections.MapUtils;
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
    @Autowired
    ArticleService articleService;
    @Autowired
    CommonService commonService;

    @RequestMapping("/personRecord")
    public Result checkLogin(@RequestParam Map params) {
        String id = MapUtils.getString(params, "id");
        String cookie = commonService.CookieDeAESC(MapUtils.getString(params, "cookie"));
        if (!articleService.checkCookieRecord(cookie)) {
            return Result.build("1", "error");
        }
        List<Map> article = articleService.selectArticleList(id);
        return Result.build("0", "ok", MyMapUtils.asMap("personRecordList", article));
    }
}
