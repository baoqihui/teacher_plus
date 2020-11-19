package com.hbq.teacher_plus.common.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.hbq.teacher_plus.common.service.FileManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.InetAddress;

@Slf4j
@Service
public class FileManageServiceImpl implements FileManageService {

    @Value("${nginxFilePath}")
    private String nginxFilePath;

    @Override
    public String uploadToNginx(MultipartFile file, String modelName)  {
        try {
            String path=modelName+ "/"+ IdUtil.simpleUUID() +"-"+file.getOriginalFilename();
            File test = new File(nginxFilePath+path);
            if (!test.exists()){
                test.mkdirs();
            }
            file.transferTo(test);
            InetAddress address = InetAddress.getLocalHost();
            String ip=address.getHostAddress();
            String finalPath="http://"+ip+"/file/"+path;
            return finalPath;
        }catch (Exception e){
            log.error(file.getOriginalFilename()+"文件上传失败", e);
            return file.getOriginalFilename()+"文件上传失败";
        }
    }
}
