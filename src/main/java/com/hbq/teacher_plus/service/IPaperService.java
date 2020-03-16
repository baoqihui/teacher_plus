package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Paper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 学生论文
 *
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
public interface IPaperService extends IService<Paper> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Paper> findList(Map<String, Object> params);
}

