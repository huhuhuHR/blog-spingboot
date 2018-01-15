package com.huorong.domain;

import lombok.Data;

/**
 * Created by huorong on 18/1/12.
 */
@Data
public class BlogShare {
    private String userId;
    private String shareId;
    private String shareUrl;
    private String shareTitle;
    private String shareDesc;// 标签
    private String createTime;
    private String updateTime;
    private String recordCount;
    private String imageId;
    private String imageName;
    private String userName;
    private String dayString;
}
