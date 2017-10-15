package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.domain.WorkRecord;
import com.huorong.service.WorkRecordService;
import org.apache.commons.collections.MapUtils;
import org.n3r.idworker.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/15.
 */
@RestController
@RequestMapping("workRecord")
public class WorkRecordController {
    @Autowired
    WorkRecordService workRecordService;

    @RequestMapping("/selectRecord")
    public Result selectRecord(@RequestParam Map params) {
        String userId = MapUtils.getString(params, "userId");
        List<WorkRecord> workRecords = workRecordService.selectRecord(userId);
        return Result.build("0", "ok", com.huorong.utils.MapUtils.asMap("workRecords", workRecords));
    }

    @RequestMapping("/saveRecord")
    public Result saveRecord(@RequestParam Map params) {
        params.put("id", Id.next());
        return workRecordService.saveRecord(params) ? Result.build("0", "ok") : Result.build("1", "error");
    }

}
