package com.huorong.domain;

import lombok.Data;

/**
 * Created by huorong on 17/7/16.
 */
@Data
public class User {
    // `username` varchar(255) DEFAULT NULL,
    // `password` varchar(255) DEFAULT NULL,
    // `email` varchar(255) DEFAULT NULL,
    // `phone` varchar(255) DEFAULT NULL,
    // `addr` varchar(255) DEFAULT NULL,
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String addr;
}
