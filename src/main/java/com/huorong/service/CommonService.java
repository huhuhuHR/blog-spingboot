package com.huorong.service;

import org.springframework.stereotype.Service;

import com.huorong.utils.secret.AESKey;
import com.huorong.utils.secret.AESUtil;

/**
 * Created by huorong on 17/9/29.
 */
@Service
public class CommonService {
    public String CookieDeAESC(String cookie) {
        try {
            cookie = AESUtil.decrypt(cookie, AESKey.AES_Key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;

    }

    public String CookieEeAESC(String cookie) {
        try {
            cookie = AESUtil.encrypt(cookie, AESKey.AES_Key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;

    }
}
