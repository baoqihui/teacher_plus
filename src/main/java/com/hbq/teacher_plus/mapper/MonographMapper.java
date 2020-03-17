package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.Monograph;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教师出版专著情况
 * 
 * @author hqb
 * @date 2020-03-17 09:29:58
 */
@Mapper
public interface MonographMapper extends SuperMapper<Monograph> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<Monograph> findList(Page<Monograph> page, @Param("p") Map<String, Object> params);
}
