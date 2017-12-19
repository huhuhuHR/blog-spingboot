package com.huorong.service;

import com.huorong.utils.secret.EDUtils;
import org.springframework.stereotype.Service;

/**
 * Created by huorong on 17/9/29.
 */
@Service
public class CommonService {
    public String CookieDeAESC(String cookie) {
        try {
            cookie = EDUtils.decrypt(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;

    }

    public String CookieEeAESC(String cookie) {
        try {
            cookie = EDUtils.encrypt(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;

    }
}
