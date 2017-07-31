package com.huorong.utils;

/**
 * Created by huorong on 17/7/30.
 */
public class ObjectUtils {
    public static boolean isEmpty(Object object) {
        return (object == null) || ("".equals(object.toString()));
    }

    public static boolean isNotEmpty(Object object) {
        return (object != null) && (!"".equals(object.toString()));
    }
}
