package com.huorong.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by huorong on 17/8/2.
 */
public class ReadFileInText {
    public static String text2String(String path) {
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String args[]) {
        System.out.println(text2String("src/main/java/com/huorong/Font/Font.text"));
    }
}
