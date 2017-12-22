package com.huorong.service;

import com.alibaba.fastjson.JSON;
import com.github.bingoohuang.excel2beans.CellData;
import com.huorong.domain.Program;
import com.huorong.utils.MapUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by huorong on 17/12/22.
 */
@Service
public class UploadExcelService {

    public String generatorJson(List<Program> programs) {
        return JSON.toJSONString(programs);
    }

    public void epsideProgram(List<Program> programs) {
        for (Program program : programs) {
            List<String> epsideDescList = program.getEpsideDesc();
            List<String> tvURLList = program.getTvURL();
            List<String> iphoneURLList = program.getIphoneURL();
            List<Map> myEpside = new ArrayList<>();
            for (int i = 0; i < tvURLList.size(); i++) {
                String tvUrl = tvURLList.get(i);
                String eposodeDesc = avoidOutOfBounds(epsideDescList, i);
                String iphoneUrl = avoidOutOfBounds(iphoneURLList, i);
                myEpside.add(MapUtils.of("eposodeDesc", eposodeDesc, "tvUrl", tvUrl, "iphoneUrl", iphoneUrl));
            }
            program.setEpside(myEpside);
            if ("样例1".equals(program.getProgramName())) {
                program.setError("样例1必须死");
            }
        }
    }

    public String avoidOutOfBounds(List<String> epsideDescList, int i) {
        String eposodeDesc;
        try {
            eposodeDesc = epsideDescList.get(i);
        } catch (Exception e) {
            eposodeDesc = "";
        }
        return eposodeDesc;
    }

    public List<Program> filterByOrderNotNull(List<Program> programs) {
        programs = programs.stream().filter((Program program) -> {
            String order = program.getOrder();
            return !Strings.isBlank(order) && !program.getOrder().contains("…");
        }).collect(Collectors.toList());
        return programs;
    }

    public void rmTailWithinList(List<Program> programs) {
        programs.forEach((Program program) -> {
            List<String> epsideDescList = program.getEpsideDesc();
            List<String> tvURLList = program.getTvURL();
            List<String> iphoneURLList = program.getIphoneURL();
            rmNullTail(epsideDescList);
            rmNullTail(tvURLList);
            rmNullTail(iphoneURLList);
        });
    }

    private void rmNullTail(List<String> content) {
        for (int i = content.size() - 1; i >= 0; i--) {
            String epsideDesc = content.get(i);
            if (Strings.isNotBlank(epsideDesc) && !"NULL".equals(epsideDesc.toUpperCase())) {
                break;
            }
            content.remove(i);
        }
    }

    public void closeStream(HttpServletResponse response, Workbook workbook, String fileName) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8'zh_cn'" + toUtf8String(fileName));
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public CellData appendComment(Program bean, String error, String fieldName) {
        CellData cellData = (CellData) bean.getCellDataMap().get(fieldName);
        if (cellData.getComment() == null)
            cellData.setComment(error);
        else {
            cellData.setComment(cellData.getComment() + "\n" + error);
        }
        return cellData;
    }
}
