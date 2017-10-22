package com.huorong.domain;

import lombok.Data;

/**
 * Created by huorong on 17/10/21.
 */
@Data
public class Experience {
    // id,resume_id,start_time,end_time,experience_record,create_time,update_time
    private String id;
    private String resumeId;
    private String startTime;
    private String endTime;
    private String experienceRecord;
    private String createTime;
    private String updateTime;
}
