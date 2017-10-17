package com.huorong.domain;

/**
 * Created by huorong on 17/10/17.
 */
public enum RandomTookitNameAndIconfont {
    RABBIT("rabbit", "icon-dongwuxue"), CAT("cat", "icon-animal"), INSECT("insect", "icon-dongwu");
    private String urlName;
    private String iconfont;

    private RandomTookitNameAndIconfont(String urlName, String iconfont) {
        this.urlName = urlName;
        this.iconfont = iconfont;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getIconfont() {
        return iconfont;
    }

    public void setIconfont(String iconfont) {
        this.iconfont = iconfont;
    }
}
