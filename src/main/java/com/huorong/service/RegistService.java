package com.huorong.service;

import com.huorong.Controller.blog1.ArticleController;
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

    private boolean hasRegistBlog(Map params) {
        int count = registDao.registBlog(params);
        return count == 1;
    }

    public boolean registResult(Map params) throws Exception {
        String blogName = MapUtils.getStr(params, "blogName");
        String blogEmail = MapUtils.getStr(params, "blogEmail");
        if (!nameHasNotRegist(blogName)) {
            log.error("用户名{}已经存在", blogName);
            throw new Exception("用户名已存在");
        }
        if (!emailHasNotRegist(blogEmail)) {
            log.info("邮件已经被注册了{}已经存在", blogName);
            throw new Exception("邮件已存在");
        }
        params.put("userId", Id.next());
        return hasRegistBlog(params);
    }

    public void sendEmailToReigst(String blogEmail, String userId) throws Exception {
        AdminEmail adminEmail = emailDao.selectSystemEmail();
        if (EmailUtil.sendEmail(blogEmail, "hhhh", adminEmail)) {
            registDao.insertEmailLog(MapUtils.of("userId", userId, "uuid", Id.next(), "state", "1"));
        } else {
            registDao.insertEmailLog(MapUtils.of("userId", userId, "uuid", Id.next(), "state", "0"));
        }
    }
}
