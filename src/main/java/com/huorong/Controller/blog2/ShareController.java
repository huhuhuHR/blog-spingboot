package com.huorong.Controller.blog2;

import com.huorong.domain.Result;
import com.huorong.service.ShareService;
import com.huorong.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("share")
public class ShareController {
    private Logger log = LoggerFactory.getLogger(ShareController.class);
    @Autowired
    ShareService shareService;

    @RequestMapping("toShare")
    public Result regist(@RequestParam Map params) {
        String shareUrl = MapUtils.getStr(params, "shareUrl");
        if (StringUtils.isEmpty(shareUrl)) {
            Result.build("1", "error");
        }

        return shareService.insertShare(params) ? Result.build("0", "ok") : Result.build("1", "error");
    }

    @RequestMapping("selectNewestShare")
    public Result selectNewestShare(@RequestParam Map params) {
        return Result.build("0", "ok", MapUtils.of("shares", shareService.selectNewestShare(params)));
    }

    @RequestMapping("uploadImag")
    public Result uploadImag(@RequestParam Map params) {
        return Result.build("0", "ok", MapUtils.of("image", shareService.uploadImage(params)));
    }

    @RequestMapping("selectShares")
    public Result selectyShares(@RequestParam String userId) {
        List<Map> shares = shareService.selectMyShare(userId);
        return Result.build("0", "ok", MapUtils.asMap("shares", shares));
    }

    @RequestMapping("delteShares")
    public Result deleteMyShare(@RequestParam String userId, @RequestParam String shareId) {
        return shareService.deleteMyShare(userId, shareId) ? Result.build("0", "ok") : Result.build("1", "error");
    }

    @RequestMapping("addShareCount")
    public void addShareCount(@RequestParam String shareId) {
        shareService.updateCountByShareId(shareId);
    }
}