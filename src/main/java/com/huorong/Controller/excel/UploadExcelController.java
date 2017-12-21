package com.huorong.Controller.excel;

import com.huorong.domain.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by huorong on 17/12/21.
 */
@RestController
@RequestMapping("excel")
public class UploadExcelController {
    @RequestMapping(value = "uploadExcel", method = RequestMethod.POST)
    public Result upload(@RequestParam("fileExel") MultipartFile fileExel) {
        String fileExelName = fileExel.getOriginalFilename();
        System.out.println(fileExelName);
        return Result.build("0", "ok");
    }
}
