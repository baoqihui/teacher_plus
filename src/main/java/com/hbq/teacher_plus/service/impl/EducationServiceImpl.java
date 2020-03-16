package com.hbq.teacher_plus.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.mapper.EducationMapper;
import com.hbq.teacher_plus.model.Education;
import com.hbq.teacher_plus.service.IEducationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-16 10:02:22
 */
@Slf4j
@Service
public class EducationServiceImpl extends ServiceImpl<EducationMapper, Education> implements IEducationService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<Education> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<Education> pages = new Page<>(page, limit);
        List<Education> list  =  baseMapper.findList(pages, params);
        return PageResult.<Education>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
