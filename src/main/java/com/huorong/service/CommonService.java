package com.huorong.service;

import org.springframework.stereotype.Service;

import com.huorong.utils.secret.AesUtils;

/**
 * Created by huorong on 17/9/29.
 */
@Service
public class CommonService {
    public String CookieDeAESC(String cookie) {
        try {
            cookie = AesUtils.decodeKey(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;

    }

    public String CookieEeAESC(String cookie) {
        try {
            cookie = AesUtils.encodeKey(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;

    }
}
