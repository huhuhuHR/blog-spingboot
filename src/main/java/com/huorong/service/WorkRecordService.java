package com.huorong.service;

import com.huorong.dao.WorkRecordDao;
import com.huorong.domain.WorkRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/15.
 */
@Service
public class WorkRecordService {
    @Autowired
    WorkRecordDao workRecordDao;

    public List<WorkRecord> selectRecord(String userId) {
        return workRecordDao.selectRecord(userId);
    }

    public boolean saveRecord(Map parms) {
        return workRecordDao.saveRecord(parms) == 1;
    }
}
