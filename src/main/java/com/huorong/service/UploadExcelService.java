package com.huorong.service;

import com.alibaba.fastjson.JSON;
import com.github.bingoohuang.excel2beans.CellData;
import com.huorong.domain.Program;
import com.huorong.utils.MapUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by huorong on 17/12/22.
 */
@Service
public class UploadExcelService {
    private File f;
    private final int SIZE_2M = 2097152;
    private ZipFile _zipFile;
    private static final Logger log = LoggerFactory.getLogger(UploadExcelService.class);
    private static final String PHOTO_SUFFIX = "750x375";
    private static final String PERIOD_SUFFIX = "216x120";
    private static final String POST_SUFFIX = "245x325";

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

    public Map<String, List> getPhotosByProgramName(MultipartFile fileZip, String type) throws Exception {
        if (fileZip == null) {
            return new HashMap<>();
        }
        Set<String> tempOrderSet = new HashSet<>();
        f = File.createTempFile("tmp", null);
        if (f.exists()) {
            if (f.delete()) {
                log.info("BatchAddProgramService--:delete success tempFile!!!");
            }
        }
        fileZip.transferTo(f);
        f.deleteOnExit();
        _zipFile = new ZipFile(f);
        Enumeration entries = _zipFile.entries();
        Map<String, List> map = new HashMap<>();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (!entry.isDirectory()) {
                String entryName = entry.getName();
                if (entryName.contains("__MACOSX/")) {
                    continue;
                }
                if (entryName.contains("/")) {
                    int indexEntryName = entryName.lastIndexOf("/");
                    entryName = entryName.substring(indexEntryName + 1, entryName.length());
                }
                String[] arr = entryName.split("-");
                String programName = arr[0];
                String photoName = entryName.split("\\.")[0];
                String suffix = photoName.split("-")[1];
                if ("0".equals(type) && PERIOD_SUFFIX.equals(suffix)) {
                    continue;
                }
                if ("1".equals(type) && POST_SUFFIX.equals(suffix)) {
                    continue;
                }
                log.info(":BatchAddProgramService--:entryName:{}", entryName);
                if (!entryName.matches("^[\\s\\S]{1,20}(-)(\\d)+(x)(\\d)+(-)(\\d)+(\\.)(jpeg|jpg|png|gif|bmp)$")) {
                    log.info(":BatchAddProgramService--:photo format  does not match:{}",
                            "ZIP压缩包中的图片：" + entryName + "，错误原因：上传图片格式不正确。");
                    continue;
                }
                // 重复照片命名
                if (!tempOrderSet.add(photoName)) {
                    log.info("BatchAddProgramService--:photo name repeat!!!,programName:{}",
                            "ZIP压缩包中的图片：" + entryName + "，错误原因：压缩包中该图片名字重复出现");
                    continue;
                }
                InputStream inputStream = _zipFile.getInputStream(entry);
                int size = inputStream.available();
                // 图片规格不符和标准
                if (size > SIZE_2M) {
                    log.info("BatchAddProgramService--:photo size more than 2 m!!!,programImportSortNumber:{},size:{}",
                            "ZIP压缩包中的图片：" + entryName + "，错误原因：该图片大小大于2M，过大", size);
                    continue;
                }
                if (!PHOTO_SUFFIX.equals(suffix) && !PERIOD_SUFFIX.equals(suffix) && !POST_SUFFIX.equals(suffix)) {
                    log.info("BatchAddProgramService--:photo suffix does not match the suffix!!!,suffix:{}",
                            "ZIP压缩包中的图片：" + entryName
                                    + "，错误原因：该图片不符合750x375／216x120命名规则。命名需符合。例子1-1-750x375.jpg或者1-1-126x120。");
                    continue;
                }
                List mapList = map.computeIfAbsent(programName, k -> new ArrayList());
                mapList.add(MapUtils.of("name", photoName, "entry", entry));
            }
        }
        return map;
    }
}
