package com.hbq.teacher_plus.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.mapper.WorkMapper;
import com.hbq.teacher_plus.model.Work;
import com.hbq.teacher_plus.service.IWorkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 工作经历
 *
 * @author hqb
 * @date 2020-03-16 16:22:02
 */
@Slf4j
@Service
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements IWorkService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<Work> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<Work> pages = new Page<>(page, limit);
        List<Work> list  =  baseMapper.findList(pages, params);
        return PageResult.<Work>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
