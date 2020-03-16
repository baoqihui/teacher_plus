package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Practical;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 应用型课程
 *
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
public interface IPracticalService extends IService<Practical> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Practical> findList(Map<String, Object> params);
}

