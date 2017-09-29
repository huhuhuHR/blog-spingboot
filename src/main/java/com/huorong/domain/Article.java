package com.huorong.domain;

import lombok.Data;

/**
 * Created by huorong on 17/9/29.
 */
@Data
public class Article {
    private String id;
    private String author;
    private String title;
    private String body;
    private String time;
    private String remove;
    private String state;
}
