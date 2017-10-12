package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.service.CommonService;
import com.huorong.service.ToolKitService;
import com.huorong.utils.MapUtils;
import org.n3r.idworker.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by huorong on 17/10/12.
 */
@RestController
@RequestMapping("ToolKit")
public class ToolKitController {
    @Autowired
    ToolKitService toolKitService;
    @Autowired
    CommonService commonService;

    @RequestMapping("inserToolKit")
    public Result insertToolKit(@RequestParam Map params) {
        MapUtils.of(params, "id", Id.next(), "userId",
                toolKitService.selectUserId(commonService.CookieDeAESC(MapUtils.getStr(params, "cookie"))));
        return toolKitService.insertToolKit(params) ? Result.build("0", "ok") : Result.build("1", "error");
    }

    @RequestMapping("selectTookies")
    public Result selectTookies(@RequestParam String userId) {
        return Result.build("0", "ok", MapUtils.of("toolKitList", toolKitService.selectTookies(userId)));
    }
}
