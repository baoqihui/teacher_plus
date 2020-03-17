package com.hbq.teacher_plus.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.mapper.MonographMapper;
import com.hbq.teacher_plus.model.Monograph;
import com.hbq.teacher_plus.service.IMonographService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 教师出版专著情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:58
 */
@Slf4j
@Service
public class MonographServiceImpl extends ServiceImpl<MonographMapper, Monograph> implements IMonographService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<Monograph> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<Monograph> pages = new Page<>(page, limit);
        List<Monograph> list  =  baseMapper.findList(pages, params);
        return PageResult.<Monograph>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
