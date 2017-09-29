package com.huorong.utils;

import lombok.Data;

import java.util.List;

/**
 * Created by huorong on 17/7/18.
 */
@Data
public class MybatisPage {
    private int currentPageNum;// 当前页
    private int pageSize;// 每页最大条数
    private long listSize;// 查询数据总数字
    private int pagesSize;// 总页数
    private List list;// 查到的数据

    @Override
    public String toString() {
        return "{" + "currentPageNum=" + currentPageNum + ", pageSize=" + pageSize + ", listSize=" + listSize
                + ", pagesSize=" + pagesSize + ", list=" + list + '}';
    }
}
