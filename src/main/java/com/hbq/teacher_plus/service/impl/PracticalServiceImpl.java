package com.hbq.teacher_plus.service.impl;
import com.hbq.teacher_plus.mapper.PracticalMapper;
import com.hbq.teacher_plus.service.IPracticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.hbq.teacher_plus.model.Practical;
import com.hbq.teacher_plus.service.IPracticalService;

/**
 * 应用型课程
 *
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
@Slf4j
@Service
public class PracticalServiceImpl extends ServiceImpl<PracticalMapper, Practical> implements IPracticalService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<Practical> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<Practical> pages = new Page<>(page, limit);
        List<Practical> list  =  baseMapper.findList(pages, params);
        return PageResult.<Practical>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
