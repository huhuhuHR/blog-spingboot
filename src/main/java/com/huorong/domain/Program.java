package com.huorong.domain;

import com.github.bingoohuang.excel2beans.annotations.ExcelColTitle;
import lombok.Data;

import java.util.List;

@Data
public class Program {
    @ExcelColTitle("序号")
    String order;
    @ExcelColTitle("节目名称")
    String programName;
    @ExcelColTitle("所属产品")
    String product;
    @ExcelColTitle("描述")
    String programDesc;
    @ExcelColTitle("简介")
    String programIntroduce;
    @ExcelColTitle("学段")
    String ageId;
    @ExcelColTitle("年级")
    String grade;
    @ExcelColTitle("科目")
    String subject;
    @ExcelColTitle("教材版本")
    String version;
    @ExcelColTitle("标签")
    String label;
    @ExcelColTitle("讲师")
    String teacher;
    @ExcelColTitle("支持单点")
    String singleClick;
    @ExcelColTitle("单点价格")
    String singlePrice;
    @ExcelColTitle("产品编码")
    String productCode;
    @ExcelColTitle("加入VIP包月")
    String joinVip;
    @ExcelColTitle("试看方式")
    String wayTrySee;
    @ExcelColTitle("剧集描述")
    List<String> epsideDesc;
    @ExcelColTitle("电视端")
    List<String> tvURL;
    @ExcelColTitle("手机端")
    List<String> iphoneURL;
}
