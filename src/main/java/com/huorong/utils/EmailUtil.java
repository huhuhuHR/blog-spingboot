package com.huorong.utils;

/**
 * Created by beck on 2016/3/9.
 */

import org.apache.commons.mail.HtmlEmail;

/**
 * Created by beck on 2015/11/25.
 */
public class EmailUtil {
    public static void sendEmail() {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.163.com");
        email.setAuthentication("maiyainternet@163.com", "maiya123456");
        email.setSubject("验证码");
        email.setCharset("UTF-8");
        try {
            email.setFrom("maiyainternet@163.com", "麦芽网络");
            email.addTo("1004836567@qq.com");
            email.setTextMsg("have some update");
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


