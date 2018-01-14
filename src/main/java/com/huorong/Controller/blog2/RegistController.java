package com.huorong.Controller.blog2;

import com.huorong.domain.Result;
import com.huorong.service.RegistService;
import com.huorong.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("regist")
public class RegistController {
    private Logger log = LoggerFactory.getLogger(RegistController.class);
    @Autowired
    RegistService registService;

    @RequestMapping("toRegist")
    public Result regist(@RequestParam Map params) {
        String checkResult = registService.checkParams(params);
        if (!StringUtils.isEmpty(checkResult)) {
            // 6 数据有问题
            return Result.build("0", "ok", MapUtils.of("result", "6", "checkResult", checkResult));
        }
        String result = registService.registResult(params);
        String userId = MapUtils.getStr(params, "userId");
        if ("2".equals(result)) {
            try {
                String uuid = registService.sendEmailToReigst(MapUtils.getStr(params, "blogEmail"), userId);
                return Result.build("0", "ok", MapUtils.of("result", result, "uuid", uuid));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("邮件记录日志异常");
            }
        }
        return Result.build("0", "ok", MapUtils.of("result", result));
    }

    @RequestMapping(value = "toActive", method = RequestMethod.POST)
    public Result toActive(@RequestParam String uuid, @RequestParam String msg) throws Exception {
        String userId = registService.toActive(uuid, msg);
        if (!StringUtils.isEmpty(userId)) {
            return Result.build("0", "ok", MapUtils.of("userId", userId));
        }
        return Result.build("1", "error");
    }

    @RequestMapping("getLoginInfo")
    public Result getLoginInfo(@RequestParam Map params) {
        return registService.getLoginInfo(params);
    }
}
