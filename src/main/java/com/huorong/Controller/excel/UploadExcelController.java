package com.huorong.Controller.excel;

import com.github.bingoohuang.excel2beans.ExcelToBeans;
import com.huorong.domain.Program;
import com.huorong.domain.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by huorong on 17/12/21.
 */
@RestController
@RequestMapping("excel")
public class UploadExcelController {
    @RequestMapping(value = "uploadExcel", method = RequestMethod.POST)
    public Result upload(@RequestParam("fileExel") MultipartFile fileExel) {
        String fileExelName = fileExel.getOriginalFilename();
        try {
            ExcelToBeans excelToBeans = new ExcelToBeans(fileExel.getInputStream());
            List<Program> program = excelToBeans.convert(Program.class);
            program.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileExelName);
        return Result.build("0", "ok");
    }
}
