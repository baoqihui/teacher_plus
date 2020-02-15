package com.hbq.teacher_plus.service.impl;
import com.hbq.teacher_plus.mapper.BasicInfoMapper;
import com.hbq.teacher_plus.service.IBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.service.IBasicInfoService;

/**
 * 基本信息
 *
 * @author hqb
 * @date 2020-02-14 17:18:15
 */
@Slf4j
@Service
public class BasicInfoServiceImpl extends ServiceImpl<BasicInfoMapper, BasicInfo> implements IBasicInfoService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<BasicInfo> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<BasicInfo> pages = new Page<>(page, limit);
        List<BasicInfo> list  =  baseMapper.findList(pages, params);
        return PageResult.<BasicInfo>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
