package com.huorong.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by huorong on 17/10/21.
 */
@Data
public class Resume {
    // id,user_id,name,sex,native_place,age,phone_number,study_level,work_year,skills,assessment,create_time,update_time
    private String id;
    private String userId;
    private String name;
    private String sex;
    private String nativePlace;
    private String age;
    private String phoneNumber;
    private String studyLevel;
    private String workYear;
    private String skills;
    private String assessment;
    private String createTime;
    private String updateTime;
    private List<Experience> workLists;
}
