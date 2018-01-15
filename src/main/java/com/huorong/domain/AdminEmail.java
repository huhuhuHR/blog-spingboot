package com.huorong.domain;

import lombok.Data;

@Data
public class AdminEmail {
    private String name;// 邮箱名字
    private String authPassword;// 163授权码
    private String evn;// dev-本地,pro-生产
}
