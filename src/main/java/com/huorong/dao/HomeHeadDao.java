package com.huorong.dao;

import com.huorong.domain.Router;

import java.util.List;
import java.util.Map;

/**
 * Created by huorong on 17/9/30.
 */
public interface HomeHeadDao {
    List<Router> routerList(Map params);
}
