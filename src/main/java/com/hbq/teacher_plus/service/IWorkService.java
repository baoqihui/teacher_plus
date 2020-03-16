package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Work;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 工作经历
 *
 * @author hqb
 * @date 2020-03-16 16:22:02
 */
public interface IWorkService extends IService<Work> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Work> findList(Map<String, Object> params);
}

