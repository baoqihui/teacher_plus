package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Monograph;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 教师出版专著情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:58
 */
public interface IMonographService extends IService<Monograph> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Monograph> findList(Map<String, Object> params);
}

