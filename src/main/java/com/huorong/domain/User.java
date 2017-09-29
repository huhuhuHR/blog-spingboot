package com.huorong.domain;

import lombok.Data;

/**
 * Created by huorong on 17/7/16.
 */
@Data
public class User {
    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String addr;
    private String state;
    private String create_time;
    private String update_time;
}
