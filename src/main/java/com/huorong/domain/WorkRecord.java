package com.huorong.domain;

import lombok.Data;

/**
 * Created by huorong on 17/10/15.
 */
@Data
public class WorkRecord {
    private String id;
    private String userId;
    private String recordBody;
    private String createTime;
    private String remove;
    private String state;
}
