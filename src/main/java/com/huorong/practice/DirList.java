package com.huorong.practice;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by huorong on 17/8/2.
 */
public class DirList {
    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {
        File filePath = new File("/Users/admin/Desktop/临时文件");
        String[] list = filePath.list(new DirFilter(".*.js"));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dir : list) {
            System.out.println(dir);
        }
        // 匿名内部类
        String[] otherList = filePath.list(filter(".*.js"));
        for (String dir : otherList) {
            System.out.println(dir);
        }
    }
}
