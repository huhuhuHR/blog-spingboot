package com.huorong.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huorong.domain.Result;
import com.huorong.service.MyService;
import com.huorong.utils.MybatisPage;
import com.huorong.utils.NewMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/7/18. mybatis 分页小例子
 */
@RestController
@RequestMapping("test")
public class TestPageController {
    @Autowired
    MyService myService;

    @RequestMapping("/pageInit")
    public Result testPage() {
        // 通过传当前页面，每页大小控制
        PageHelper.startPage(2, 3);
        List<Map> list = myService.findAllUser();
        PageInfo page = new PageInfo(list);
        return Result.build("0", "ok", NewMapUtils.asMap("page", MybatisPage.bulid(page)));
    }
}
