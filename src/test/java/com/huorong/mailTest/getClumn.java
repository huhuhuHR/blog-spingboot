package com.huorong.mailTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getClumn {
    public static void main(String[] args) throws Exception {
        File file = new File("src/test/java/com/huorong/mailTest/resource.txt");
        InputStream in = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            sb.append(s);
        }
        String resource = sb.toString();
        resource = resource.replace(" ", "");
        System.out.println(resource);
        String regex = "^,[a-zA-Z_]+`$"; // 正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(resource);
        List<String> matchRegexList = new ArrayList<String>();
        while (m.find()) {
            matchRegexList.add(m.group());
        }
        matchRegexList.forEach(System.out::println);
    }
}
