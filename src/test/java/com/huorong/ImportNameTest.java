package com.huorong;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huorong on 17/11/28.
 */
public class ImportNameTest {
    /**
     * Excel 2003
     */
    private final static String XLS = "xls";
    /**
     * Excel 2007
     */
    private final static String XLSX = "xlsx";
    /**
     * 分隔符
     */
    private final static String SEPARATOR = "|";

    /**
     * 由Excel文件的Sheet导出至List
     *
     * @param file
     * @param sheetNum
     * @return
     */
    public static List<String> exportListFromExcel(File file, int sheetNum) throws IOException {
        return exportListFromExcel(new FileInputStream(file), XLSX, sheetNum);
    }

    /**
     * 由Excel流的Sheet导出至List
     *
     * @param is
     * @param extensionName
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public static List<String> exportListFromExcel(InputStream is, String extensionName, int sheetNum)
            throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromExcel(workbook, sheetNum);
    }

    /**
     * 由指定的Sheet导出至List
     *
     * @param workbook
     * @param sheetNum
     * @return
     * @throws IOException
     */
    private static List<String> exportListFromExcel(Workbook workbook, int sheetNum) {

        Sheet sheet = workbook.getSheetAt(sheetNum);

        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        List<String> list = new ArrayList<String>();

        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            StringBuilder sb = new StringBuilder();

            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null) {
                    continue;
                }
                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
                switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    sb.append(SEPARATOR + cellValue.getBooleanValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                    if (DateUtil.isCellDateFormatted(cell)) {
                        sb.append(SEPARATOR + cell.getDateCellValue());
                    } else {
                        // 把手机号码转换为字符串
                        DecimalFormat df = new DecimalFormat("#");
                        sb.append(SEPARATOR + df.format(cellValue.getNumberValue()));
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    sb.append(SEPARATOR + cellValue.getStringValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    break;
                default:
                    break;
                }
            }
            list.add(sb.toString());
        }
        return list;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String path = "/Users/admin/Documents/github/springboot/springboot/src/test/java/com/huorong/标准版模版.xlsx";
        try {
            List<String> listS = exportListFromExcel(new File(path), 0);
            for (int i = 0; i < listS.size(); i++) {
                System.out.println(listS.get(i));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
