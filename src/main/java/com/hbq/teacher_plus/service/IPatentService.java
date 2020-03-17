package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Patent;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 教师获批专利情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
public interface IPatentService extends IService<Patent> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Patent> findList(Map<String, Object> params);
}

