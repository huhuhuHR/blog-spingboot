package com.huorong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by huorong on 18/1/31.
 */
@Service
public class BaseService {
    @Autowired
    private Environment env;

    public String imageName(String imageId) {
        String share_evn = env.getProperty("share_evn");
        String result = "";
        if ("dev".equals(share_evn)) {
            result = "http://localhost:7002/image/" + imageId;
        } else if ("pro".equals(share_evn)) {
            result = "/image/" + imageId;
        }
        return result;
    }
}
