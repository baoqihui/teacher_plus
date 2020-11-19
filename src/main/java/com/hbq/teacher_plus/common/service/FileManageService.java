package com.hbq.teacher_plus.common.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileManageService {

    String uploadToNginx(MultipartFile file, String modelName);
}
