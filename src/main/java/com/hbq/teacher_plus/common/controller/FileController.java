package com.hbq.teacher_plus.common.controller;

import com.hbq.teacher_plus.common.service.FileManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
@Api(tags = "文件服务")
public class FileController {
    @Autowired
    private FileManageService fileManageService;
    
    @ApiOperation(value = "上传")
    @PostMapping("/uploadToNginx")
    public String uploadToNginx(@RequestParam("file") MultipartFile file, String modelName) {
        if (file==null){
            return "文件上传失败，请重新选择文件";
        }
        return fileManageService.uploadToNginx(file,modelName);
    }

}
