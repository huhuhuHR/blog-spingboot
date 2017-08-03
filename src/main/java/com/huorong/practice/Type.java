package com.huorong.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huorong on 17/8/3.
 */
public class Type {
    private Object[] objects;

    public Type(Object[] objects) {
        this.objects = objects;
    }

    public Object[] type(TypeFilter filter) {
        if (objects == null || filter == null) {
            return objects;
        }
        List<Object> v = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            if (filter.accept(objects[i])) {
                v.add(objects[i]);
            }
        }
        return v.toArray();
    }
}
