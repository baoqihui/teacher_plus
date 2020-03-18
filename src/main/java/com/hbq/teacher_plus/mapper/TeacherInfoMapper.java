package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.TeacherInfo;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbq.teacher_plus.vo.TeacherInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hqb
 * @date 2020-03-16 17:24:27
 */
@Mapper
public interface TeacherInfoMapper extends SuperMapper<TeacherInfo> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<TeacherInfo> findList(Page<TeacherInfo> page, @Param("p") Map<String, Object> params);
    List<TeacherInfoVo> findList2(Page<TeacherInfoVo> page, @Param("p") Map<String, Object> params);
}
