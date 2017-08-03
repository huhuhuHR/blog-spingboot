package com.huorong.practice;

/**
 * Created by huorong on 17/8/3.
 */
public class TypeTest {
    public static TypeFilter filter() {
        return new TypeFilter() {
            @Override
            public boolean accept(Object name) {
                if (name instanceof Integer) {
                    return true;
                }
                return false;
            }
        };
    }

    public static void main(String[] args) {
        Object[] objects = new Object[] { 1, 2, 4, 5, 5, "1", "2", "3", "4", "5", 55 };
        Type type = new Type(objects);
        Object[] result = type.type(filter());
        for (Object dir : result) {
            System.out.println(dir);
        }
    }
}
