package com.huorong.domain;

import lombok.Data;

@Data
public class AdminEmail {
    private String name;// 邮箱名字
    private String authPassword;// 163授权码
}
