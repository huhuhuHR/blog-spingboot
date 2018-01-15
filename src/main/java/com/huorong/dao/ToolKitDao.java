package com.huorong.dao;

import com.huorong.domain.Toolkit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/10/12.
 */
public interface ToolKitDao {
    int insertToolKit(Map params);

    String selectUserId(@Param("cookie") String cookie);

    List<Toolkit> selectTookies(@Param("userId") String userId);

    List<Toolkit> searchKey(Map params);

    int updateCountById(Map params);

    int deleteToolkit(Map params);
}
