package com.huorong.dao;

import com.huorong.domain.Experience;
import com.huorong.domain.Resume;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/21.
 */
public interface ResumeDao {
    int submitResume(Map params);

    int submitexpErience(Map params);

    int deleteByUserId(String userId);

    List<Experience> selectExperienceByUserId(String userId);

    Resume selectResumeByUserId(String userId);
}
