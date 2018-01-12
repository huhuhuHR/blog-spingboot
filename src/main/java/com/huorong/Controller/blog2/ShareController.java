package com.huorong.Controller.blog2;

import com.huorong.domain.Result;
import com.huorong.service.ShareService;
import com.huorong.utils.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("share")
public class ShareController {
    private Logger log = LoggerFactory.getLogger(ShareController.class);
    @Autowired
    ShareService shareService;

    @RequestMapping("toShare")
    public Result regist(@RequestParam Map params) {
        return shareService.insertShare(params) ? Result.build("0", "ok") : Result.build("1", "error");
    }

    @RequestMapping("selectNewestShare")
    public Result selectNewestShare(@RequestParam Map params) {
        return Result.build("0", "ok", MapUtils.of("shares", shareService.selectNewestShare(params)));
    }
}