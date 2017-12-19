package com.huorong.dao;

import com.huorong.domain.AdminEmail;

/**
 * 邮件
 * 
 * @author huorong
 */
public interface EmailDao {
    AdminEmail selectSystemEmail();
}
