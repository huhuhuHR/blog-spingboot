package com.huorong.domain;

import lombok.Data;

@Data
public class BlogUser {
    private String userId;// 唯一标识
    private String blogName;// 用户名字
    private String blogPassword;// 密码
    private String userEmail;// 注册邮箱
    private String blogCreateTime;// 创建时间
    private String blogUpdateTime;// 更新时间
    private String state;// 0未激活 1激活
    private String level;// 0 root 1 admin 2 user
    private String val1;// 预留字段
    private String val2;// 预留字段
}
