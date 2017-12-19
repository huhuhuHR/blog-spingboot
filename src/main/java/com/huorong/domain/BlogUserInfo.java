package com.huorong.domain;

import lombok.Data;

@Data
public class BlogUserInfo {
    private String userId;
    private String userName;
    private String userDesc;
    private String userPhotoName;
    private String createTime;
    private String updateTime;
}
