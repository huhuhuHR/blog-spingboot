package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.service.CommonService;
import com.huorong.service.LoginService;
import com.huorong.service.redis.RedisService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * Created by huorong on 17/7/16.
 */
@RestController
@RequestMapping("toLogin")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    CommonService commonService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/login")
    public Result checkLogin(@RequestParam Map params) {
        String name = MapUtils.getString(params, "name");
        String password = MapUtils.getString(params, "password");
        Map result = loginService.checkUser(name, password);
        if (!MapUtils.getBooleanValue(result, "flag")) {
            return Result.build("1", "error");
        }
        String id = MapUtils.getString(result, "id");
        String cookie = UUID.randomUUID().toString();
        loginService.insertCokie(com.huorong.utils.MapUtils.of("id", id, "cookie", cookie, "time", "30"));
        redisService.setStrExpreHour("loginKey", cookie, 1);
        // System.out.println(">>>REDIS>>>>>>>" +
        // redisService.getStr("loginKey"));
        return Result.build("0", "ok",
                com.huorong.utils.MapUtils.asMap("cookie", commonService.CookieEeAESC(cookie), "id", id));
    }
}
