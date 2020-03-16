package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.Education;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hqb
 * @date 2020-03-16 10:02:22
 */
@Mapper
public interface EducationMapper extends SuperMapper<Education> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<Education> findList(Page<Education> page, @Param("p") Map<String, Object> params);
}
