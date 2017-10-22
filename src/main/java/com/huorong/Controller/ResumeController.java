package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.domain.Resume;
import com.huorong.service.ResumeService;
import com.huorong.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by huorong on 17/10/21.
 */
@RestController
@RequestMapping("resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @RequestMapping("submit")
    public Result submitResume(@RequestParam Map params) {
        return resumeService.sumbit(params) ? Result.build("0", "ok") : Result.build("1", "error");
    }

    @RequestMapping("selectInit")
    public Result selectResume(@RequestParam String userId) {
        Resume resume = resumeService.selectResumeByUserId(userId);
        return resume != null ? Result.build("0", "ok", MapUtils.of("personDetail", resume))
                : Result.build("1", "error");
    }
}
