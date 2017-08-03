package com.huorong.Controller;

import com.huorong.domain.Result;
import com.huorong.service.LoginService;
import com.huorong.utils.MyMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by huorong on 17/7/16.
 */
@RestController
@RequestMapping("toLogin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public Result checkLogin(@RequestParam Map params) {
        if (!loginService.checkUser(params)) {
            return Result.build("1", "error");
        }
        return Result.build("0", "ok", MyMapUtils.asMap("result", "success"));
    }
}
