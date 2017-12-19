package com.huorong.utils.secret;

import com.github.bingoohuang.utils.crypto.Aes;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Created by huorong on 17/12/19.
 */
public class AesUtils {
    private static final String KEY = "vFrItmxI9ct8JbAg";

    public static String encodeKey(String content) {
        return Aes.encrypt(content, Aes.getKey(Base64.encode(KEY.getBytes())));
    }

    public static String decodeKey(String content) {
        return Aes.decrypt(content, Aes.getKey(Base64.encode(KEY.getBytes())));
    }

    public static void main(String[] args) {
        String ecodeHuorong = encodeKey("huorong");
        System.out.println("huorong加密：" + ecodeHuorong);
        System.out.println("解密：" + decodeKey(ecodeHuorong));
        System.out.println("!!!!!!!!");
        System.out.println("huorong加密：" + encodeKey("huorong"));
        System.out.println("Niejing加密：" + encodeKey("Niejing"));
    }
}
