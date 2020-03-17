package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.TeacherPaper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 教师发表学术论文情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
public interface ITeacherPaperService extends IService<TeacherPaper> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<TeacherPaper> findList(Map<String, Object> params);
}

