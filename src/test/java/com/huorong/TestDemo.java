package com.huorong;

import com.huorong.utils.secret.AESKey;
import com.huorong.utils.secret.AESUtil;

/**
 * Created by huorong on 17/7/17.
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(AESUtil.decrypt("AA459B38F08A48D46CA8D4C13CAA494F", AESKey.AES_Key));
        System.out.println(AESUtil.decrypt("B2EE68065CEE5A33B78B956EBB494E08", AESKey.AES_Key));
    }
}
