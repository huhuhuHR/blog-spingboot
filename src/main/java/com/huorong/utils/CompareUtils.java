package com.huorong.utils;

import org.apache.commons.collections.MapUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/7/30.
 */
public class CompareUtils {
    public static void sortOfResult(List<Map> list, String key) {
        list.stream().forEach(m -> {
            m.put("key", key);
        });
        Collections.sort(list, new Comparator<Map>() {
            @Override
            public int compare(Map p1, Map p2) {
                Double saveMoneyP1 = Double.parseDouble(MapUtils.getString(p1, "key"));
                Double saveMoneyP2 = Double.parseDouble(MapUtils.getString(p2, "key"));
                if (saveMoneyP1 >= saveMoneyP2) {
                    return -1;
                }
                return 1;
            }
        });
        list.stream().forEach(m -> {
            m.remove("key");
        });
    }
}
