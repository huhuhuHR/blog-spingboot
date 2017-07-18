package com.huorong;

import com.huorong.utils.RegexUtils;

/**
 * Created by huorong on 17/7/17.
 */
public class TestDemo {
    public static void main(String[] args) {
        System.out.println(RegexUtils.checkEmail("zhangsan@gtexpress.cn"));
        System.out.println(RegexUtils.checkEmail("zhangsan@xxx.com.cn"));
        System.out.println(RegexUtils.checkIdCard("3201221993051344"));
    }
}
