package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Education;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-16 10:02:22
 */
public interface IEducationService extends IService<Education> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Education> findList(Map<String, Object> params);
}

