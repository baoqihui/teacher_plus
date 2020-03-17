package com.hbq.teacher_plus.service.impl;
import com.hbq.teacher_plus.mapper.PatentMapper;
import com.hbq.teacher_plus.service.IPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.hbq.teacher_plus.model.Patent;
import com.hbq.teacher_plus.service.IPatentService;

/**
 * 教师获批专利情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
@Slf4j
@Service
public class PatentServiceImpl extends ServiceImpl<PatentMapper, Patent> implements IPatentService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<Patent> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<Patent> pages = new Page<>(page, limit);
        List<Patent> list  =  baseMapper.findList(pages, params);
        return PageResult.<Patent>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
