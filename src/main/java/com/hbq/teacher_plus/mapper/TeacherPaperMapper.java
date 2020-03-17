package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.TeacherPaper;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教师发表学术论文情况
 * 
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
@Mapper
public interface TeacherPaperMapper extends SuperMapper<TeacherPaper> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<TeacherPaper> findList(Page<TeacherPaper> page, @Param("p") Map<String, Object> params);
}
