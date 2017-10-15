package com.huorong.dao;

import com.huorong.domain.WorkRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/15.
 */
public interface WorkRecordDao {
    List<WorkRecord> selectRecord(String userId);

    int saveRecord(Map params);
}
