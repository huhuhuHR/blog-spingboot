package com.huorong.service;

import com.huorong.dao.EmailDao;
import com.huorong.dao.RegistDao;
import com.huorong.domain.AdminEmail;
import com.huorong.utils.EmailUtil;
import com.huorong.utils.MapUtils;
import org.n3r.idworker.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
     * 1邮件已存在
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
        String evn = adminEmail.getEvn();
        String uuid = String.valueOf(Id.next());
        String url = gendarUrl(evn, uuid);
        if (EmailUtil.sendEmail(blogEmail, url, adminEmail)) {
            registDao.insertEmailLog(MapUtils.of("userId", userId, "uuid", uuid, "state", "1", "msg", url));
            return "2";
        } else {
            registDao.insertEmailLog(MapUtils.of("userId", userId, "uuid", uuid, "state", "0", "msg", url));
            return "4";
        }
    }

    public String gendarUrl(String evn, String uuid) {
        String url = "dev".equals(evn) ? "http://localhost:1111/regist/toActive"
                : "http://www.huorong.group:1111/regist/toActive";
        url = url + "?uuid=" + uuid;
        return url;
    }

    public String getEvn() {
        AdminEmail adminEmail = emailDao.selectSystemEmail();
        return adminEmail.getEvn();
    }

    public int toActive(String uuid) {
        String userId = registDao.selectUserId(uuid);
        return registDao.toActive(userId);
    }
}
