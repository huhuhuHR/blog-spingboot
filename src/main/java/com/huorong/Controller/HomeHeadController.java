package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.domain.WorkRecord;
import com.huorong.service.HomeHeadService;
import com.huorong.service.WorkRecordService;
import com.huorong.utils.MapUtils;
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
    @Autowired
    HomeHeadService homeHeadService;
    @Autowired
    WorkRecordService workRecordService;

    @RequestMapping("/headList")
    public Result headList(@RequestParam Map params) {
        String id = MapUtils.getStr(params, "id");
        List<Map> article = homeHeadService.selectArticleList(id).subList(0, 5);
        List<WorkRecord> workRecords = workRecordService.selectRecord(id);
        if (workRecords.size() > 6) {
            workRecords = workRecords.subList(0, 10);
        }
        return Result.build("0", "ok", MapUtils.asMap("routerList", homeHeadService.routerList(params), "articleList",
                article, "workRecords", workRecords));
    }
}
