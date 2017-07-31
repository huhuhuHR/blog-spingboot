package com.huorong.utils;

import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/7/27.
 */
public class ListUtils {
    public static List<List<Map>> groupList(List<Map> list, String groupKey) {
        List<List<Map>> contents = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            String key = MapUtils.getString(list.get(i), groupKey);
            int j = 0;
            for (; j < contents.size(); j++) {
                // 遍历容器contents 集合
                List<Map> mapList = contents.get(j);
                boolean flag = MapUtils.getString(mapList.get(0), groupKey).equals(key);
                // flag == true 证明 该条数据的key属于该list
                if (flag) {
                    mapList.add(list.get(i));
                    // contents中有更新就跳出
                    break;
                }
            }
            // j == contents.size()证明是正常循环的结束，需要新增子list
            if (j == contents.size()) {
                List newList = new ArrayList();
                newList.add(list.get(i));
                contents.add(newList);
            }
        }
        return contents;
    }

}
