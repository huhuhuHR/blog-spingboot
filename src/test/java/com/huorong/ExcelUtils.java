package com.huorong;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by beck on 2017/11/10.
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                cellValue = cell.getNumericCellValue() + "";
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    // data格式是带时分秒的：2013-7-10 0:00:00
                    // cellvalue = cell.getDateCellValue().toLocaleString();
                    // data格式是不带带时分秒的：2013-7-10
                    Date date = cell.getDateCellValue();
                    cellValue = date;
                    break;
                }
                cellValue = cell.getCellFormula() + "";
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
            }
        }

        return cellValue;
    }
}