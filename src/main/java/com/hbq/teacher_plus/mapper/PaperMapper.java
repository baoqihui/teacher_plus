package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.Paper;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 学生论文
 * 
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
@Mapper
public interface PaperMapper extends SuperMapper<Paper> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<Paper> findList(Page<Paper> page, @Param("p") Map<String, Object> params);
}
