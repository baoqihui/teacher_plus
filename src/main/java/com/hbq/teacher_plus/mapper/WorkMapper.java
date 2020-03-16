package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.Work;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 工作经历
 * 
 * @author hqb
 * @date 2020-03-16 16:22:02
 */
@Mapper
public interface WorkMapper extends SuperMapper<Work> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<Work> findList(Page<Work> page, @Param("p") Map<String, Object> params);
}
