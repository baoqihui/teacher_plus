package com.hbq.teacher_plus.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.mapper.TeacherInfoMapper;
import com.hbq.teacher_plus.model.TeacherInfo;
import com.hbq.teacher_plus.service.ITeacherInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-16 17:24:27
 */
@Slf4j
@Service
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoMapper, TeacherInfo> implements ITeacherInfoService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<TeacherInfo> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<TeacherInfo> pages = new Page<>(page, limit);
        List<TeacherInfo> list  =  baseMapper.findList(pages, params);
        return PageResult.<TeacherInfo>builder().data(list).code(0).count(pages.getTotal()).build();
    }
    public PageResult<TeacherInfo> findList2(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<TeacherInfo> pages = new Page<>(page, limit);
        List<TeacherInfo> list  =  baseMapper.findList2(pages, params);
        return PageResult.<TeacherInfo>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
