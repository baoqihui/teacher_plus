package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.Practical;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 应用型课程
 * 
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
@Mapper
public interface PracticalMapper extends SuperMapper<Practical> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<Practical> findList(Page<Practical> page, @Param("p") Map<String, Object> params);
}
