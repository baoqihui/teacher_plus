package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbq.teacher_plus.vo.BasicInfoVo;

import java.util.Map;

/**
 * 基本信息
 *
 * @author hqb
 * @date 2020-02-14 17:18:15
 */
public interface IBasicInfoService extends IService<BasicInfo> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<BasicInfo> findList(Map<String, Object> params);
    PageResult<BasicInfoVo> findList2(Map<String, Object> params);
}

