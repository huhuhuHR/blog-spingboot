package com.huorong.service;

import com.huorong.dao.ResumeDao;
import com.huorong.domain.Experience;
import com.huorong.domain.Resume;
import com.huorong.utils.MapUtils;
import org.n3r.idworker.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/21.
 */
@Service
public class ResumeService {
    @Autowired
    ResumeDao resumeDao;

    @Transactional
    public boolean sumbit(Map params) {
        String userId = MapUtils.getStr(params, "id");
        resumeDao.deleteByUserId(userId);
        String resumeId = String.valueOf(Id.next());
        Map personDetail = MapUtils.jsonToMap(params, "personDetail");
        return insertResume(params, userId, resumeId, personDetail) && insertExperience(resumeId, personDetail);
    }

    private boolean insertResume(Map params, String userId, String resumeId, Map personDetailMap) {
        personDetailMap.put("id", resumeId);
        personDetailMap.put("userId", userId);
        return submitResume(personDetailMap);
    }

    private boolean insertExperience(String resumeId, Map personDetailMap) {
        List<Map> workLists = MapUtils.getListForce(personDetailMap, "workLists");
        List<Boolean> list = new ArrayList();
        workLists.stream().forEach(workList -> {
            String experienceId = String.valueOf(Id.next());
            workList.put("id", experienceId);
            workList.put("resumeId", resumeId);
            list.add(submitexpErience(workList));
        });
        return !list.contains(false);
    }

    public boolean submitResume(Map params) {
        return resumeDao.submitResume(params) == 1;
    }

    public boolean submitexpErience(Map params) {
        return resumeDao.submitexpErience(params) == 1;
    }

    public List<Experience> selectExperienceByUserId(String userId) {
        return resumeDao.selectExperienceByUserId(userId);
    }

    public Resume selectResumeByUserId(String userId) {
        List<Experience> workLists = selectExperienceByUserId(userId);
        Resume resume = resumeDao.selectResumeByUserId(userId);
        resume.setWorkLists(workLists);
        return resume;
    }
}
