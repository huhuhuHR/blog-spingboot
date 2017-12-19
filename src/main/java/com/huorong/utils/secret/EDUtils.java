package com.huorong.utils.secret;

import com.github.bingoohuang.utils.crypto.Aes;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * Created by huorong on 17/12/19.
 */
public class EDUtils {
    private static final String KEY = "vFrItmxI9ct8JbAg";

    public static String ncodeKey(String content) {
        return Aes.encrypt(content, Aes.getKey(Base64.encode(KEY.getBytes())));
    }

    public static String ecodeKey(String content) {
        return Aes.decrypt(content, Aes.getKey(Base64.encode(KEY.getBytes())));
    }

    /**
     * 加密方法
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        // 将内容转换成字符数组
        char[] charArray = content.toCharArray();
        // 遍历出所有字符
        for (int i = 0; i < charArray.length; i++) {
            // 对所有字符进行 + 1操作
            charArray[i] = (char) (charArray[i] + 1);
        }
        return new String(charArray);
    }

    /**
     * 解密方法
     *
     * @param encryptData
     * @return
     */
    public static String decrypt(String encryptData) {
        // 将内容转换成字符数组
        char[] charArray = encryptData.toCharArray();
        System.out.println(new String(charArray));
        // 解密
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] - 1);
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        // String ecodeHuorong = encodeKey("huorong");
        // System.out.println("huorong加密：" + ecodeHuorong);
        // System.out.println("解密：" + decodeKey(ecodeHuorong));
        // System.out.println("!!!!!!!!");
        // System.out.println("huorong加密：" + encodeKey("huorong"));
        // System.out.println("Niejing加密：" + encodeKey("Niejing"));
        // DES();
        System.out.println("huorong加密：" + encrypt("huorong"));
        System.out.println("Niejing加密：" + encrypt("Niejing"));
    }

    static String src = "Hello,sahadev!";

    public static void DES() {

        try {
            // 以DES的方式初始化Key生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);// 设置密钥的长度为56位
            // 生成一个Key
            SecretKey generateKey = keyGenerator.generateKey();
            // 转变为字节数组
            byte[] encoded = generateKey.getEncoded();
            // 生成密钥字符串
            String encodeHexString = Hex.encodeHexString(encoded);
            System.out.println("Key ： " + encodeHexString);
            // 再把我们的字符串转变为字节数组，可以用于另一方使用，验证
            byte[] decodeHex = Hex.decodeHex(encodeHexString.toCharArray());
            // 生成密钥对象
            SecretKeySpec secretKeySpec = new SecretKeySpec(decodeHex, "DES");

            // 获取加解密实例
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 初始化加密模式
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 加密
            byte[] doFinal = cipher.doFinal(src.getBytes());
            System.out.println("加密结果 : " + new HexBinaryAdapter().marshal(doFinal));

            // 初始化解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            // 解密
            byte[] doFinal2 = cipher.doFinal(doFinal);
            // 输出解密结果
            System.out.println("解密结果 : " + new String(doFinal2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}