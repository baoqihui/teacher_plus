package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.TeacherInfo;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-16 17:24:27
 */
public interface ITeacherInfoService extends IService<TeacherInfo> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<TeacherInfo> findList(Map<String, Object> params);
    PageResult<TeacherInfo> findList2(Map<String, Object> params);
}

