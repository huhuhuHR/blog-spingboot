package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.domain.WorkRecord;
import com.huorong.service.HomeHeadService;
import com.huorong.service.WorkRecordService;
import com.huorong.utils.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/9/30.
 */
@RestController
@RequestMapping("home")
public class HomeHeadController {
    private static final Logger log = LoggerFactory.getLogger(HomeHeadController.class);
    @Autowired
    HomeHeadService homeHeadService;
    @Autowired
    WorkRecordService workRecordService;

    @RequestMapping("/headList")
    public Result headList(@RequestParam Map params) {
        List<Map> article = null;
        List<WorkRecord> workRecords = null;
        try {
            String id = MapUtils.getStr(params, "id");
            article = homeHeadService.selectArticleList(id).subList(0, 5);
            workRecords = workRecordService.selectRecord(id);
            if (workRecords.size() > 6) {
                workRecords = workRecords.subList(0, 10);
            }
        } catch (Exception e) {
            log.debug("huorong" + e);
        }
        return Result.build("0", "ok", MapUtils.asMap("routerList", homeHeadService.routerList(params), "articleList",
                article, "workRecords", workRecords));
    }
}
