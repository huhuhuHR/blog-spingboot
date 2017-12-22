package com.huorong.Controller.excel;

import com.alibaba.fastjson.JSONObject;
import com.github.bingoohuang.excel2beans.BeansToExcel;
import com.github.bingoohuang.excel2beans.CellData;
import com.github.bingoohuang.excel2beans.ExcelToBeans;
import com.github.bingoohuang.excel2beans.ExcelToBeansUtils;
import com.google.common.collect.Sets;
import com.huorong.domain.Program;
import com.huorong.domain.Result;
import com.huorong.service.UploadExcelService;
import com.huorong.service.redis.Redis;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by huorong on 17/12/21.
 */
@RestController
@RequestMapping("excel")
public class UploadExcelController {
    @Autowired
    UploadExcelService uploadExcelService;

    @RequestMapping(value = "uploadExcel", method = RequestMethod.POST)
    public Result upload(@RequestParam("fileExel") MultipartFile fileExel) {
        String fileExelName = fileExel.getOriginalFilename();
        List<Program> programs = new ArrayList<>();
        try {
            ExcelToBeans excelToBeans = new ExcelToBeans(fileExel.getInputStream());
            programs = excelToBeans.convert(Program.class);
            programs = uploadExcelService.filterByOrderNotNull(programs);
            uploadExcelService.rmTailWithinList(programs);
            uploadExcelService.epsideProgram(programs);
            programs = programs.stream().filter((Program program) -> Strings.isNotEmpty(program.getError()))
                    .collect(Collectors.toList());
            String jsonString = uploadExcelService.generatorJson(programs);
            Redis.setex("huorong", jsonString, 2, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(fileExelName);
        return Result.build("0", "ok", programs);
    }

    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, @RequestParam String keyWord) {
        String jsonString = Redis.get(keyWord);
        List<Program> programs = JSONObject.parseArray(jsonString, Program.class);
        val workbook = new BeansToExcel().create(programs);
        val cellDatas = Sets.<CellData> newIdentityHashSet();
        for (Program program : programs) {
            cellDatas.add(uploadExcelService.appendComment(program, "hhhh", "节目名称"));
        }
        ExcelToBeansUtils.writeRedComments(workbook, cellDatas);
        uploadExcelService.closeStream(response, workbook, "aaa.xlsx");
    }
}
