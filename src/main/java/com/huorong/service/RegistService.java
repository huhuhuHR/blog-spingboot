package com.huorong.service;

import com.huorong.dao.EmailDao;
import com.huorong.dao.RegistDao;
import com.huorong.domain.AdminEmail;
import com.huorong.domain.Result;
import com.huorong.utils.EmailUtil;
import com.huorong.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RegistService {
    private Logger log = LoggerFactory.getLogger(RegistService.class);
    @Autowired
    RegistDao registDao;
    @Autowired
    EmailDao emailDao;

    private boolean nameHasNotRegist(String blogName) {
        int count = registDao.selectBlogByName(blogName);
        return count == 0;
    }

    private boolean emailHasNotRegist(String blogEmail) {
        int count = registDao.selectBlogByEmail(blogEmail);
        return count == 0;
    }

    private String hasRegistBlog(Map params) {
        try {
            int count = registDao.registBlog(params);
            return count == 1 ? "2" : "3";
        } catch (Exception e) {
            e.printStackTrace();
            return "4";
        }
    }

    /**
     * 0 用户名已存在
     * 
     * 1邮箱子已存在
     * 
     * 2成功
     * 
     * 3落值失败
     * 
     * 4系统异常
     * 
     * @param params
     * @return
     */
    public String registResult(Map params) {
        String blogName = MapUtils.getStr(params, "blogName");
        String blogEmail = MapUtils.getStr(params, "blogEmail");
        if (!nameHasNotRegist(blogName)) {
            log.error("用户名{}已经存在", blogName);
            return "0";
        }
        if (!emailHasNotRegist(blogEmail)) {
            log.info("邮件已经被注册了{}已经存在", blogName);
            return "1";
        }
        params.put("userId", Id.next());
        return hasRegistBlog(params);
    }

    public String sendEmailToReigst(String blogEmail, String userId) throws Exception {
        AdminEmail adminEmail = emailDao.selectSystemEmail();
        String key = String.valueOf(Id.next());
        String msg = "激活命令：" + key;
        String uuid = String.valueOf(UUID.randomUUID());
        try {
            EmailUtil.sendEmailAsyn(blogEmail, msg, adminEmail);
            registDao.insertEmailLog(MapUtils.of("userId", userId, "uuid", uuid, "state", "1", "msg", key));
        } catch (Exception e) {
            e.printStackTrace();
            registDao.insertEmailLog(MapUtils.of("userId", userId, "uuid", uuid, "state", "0", "msg", key));
        }
        return uuid;
    }

    public String gendarUrl(String evn, String uuid) {
        String url = "dev".equals(evn) ? "http://localhost:1110/regist/toActive"
                : "http://www.huorong.group:1110/regist/toActive";
        url = url + "?uuid=" + uuid;
        return url;
    }

    public String getEvn() {
        AdminEmail adminEmail = emailDao.selectSystemEmail();
        return adminEmail.getEvn();
    }

    public String toActive(String uuid, String msg) {
        String userId = registDao.selectUserId(uuid, msg);
        if (registDao.toActive(userId) == 1) {
            return userId;
        }
        return null;
    }

    public String checkParams(Map params) {
        String blogName = MapUtils.getStr(params, "blogName");
        if (StringUtils.isEmpty(blogName)) {
            return "用户名不能为空;";
        }
        String blogEmail = MapUtils.getStr(params, "blogEmail");
        if (StringUtils.isEmpty(blogEmail)) {
            return "邮件名不能为空;";
        }
        if (!blogEmail.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$")) {
            return "邮件格式错误;";
        }
        String blogPassword = MapUtils.getStr(params, "blogPassword");
        if (StringUtils.isEmpty(blogPassword)) {
            return "密码不能为空;";
        }
        return "";
    }

    public Result getLoginInfo(Map params) {
        Map loginInfo = registDao.getLoginInfo(params);
        String password = MapUtils.getStr(params, "password");
        if (loginInfo != null) {
            String userId = MapUtils.getStr(loginInfo, "userId");
            String state = MapUtils.getStr(loginInfo, "state");
            String relPassword = MapUtils.getStr(loginInfo, "password");
            if (!password.equals(relPassword)) {
                return Result.build("0", "ok", MapUtils.of("result", "0", "msg", "密码错误"));
            }
            if ("1".equals(state)) {
                // 登录成功
                return Result.build("0", "ok", MapUtils.of("result", "1", "userId", userId));
            } else {
                // 未激活s
                return Result.build("0", "ok",
                        MapUtils.of("result", "2", "msg", "未激活，请先激活", "uuid", registDao.selectUUidByUserId(userId)));
            }
        }
        // 未注册
        return Result.build("0", "ok", MapUtils.of("result", "0", "msg", "无效用户名"));
    }

    public List<Map> getMembers() {
        List<Map> members = registDao.getMembers();
        if (members == null) {
            members = new ArrayList<>();
        }
        return members;
    }
}
