package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 基本信息
 * 
 * @author hqb
 * @date 2020-02-14 17:18:15
 */
@Mapper
public interface BasicInfoMapper extends SuperMapper<BasicInfo> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<BasicInfo> findList(Page<BasicInfo> page, @Param("p") Map<String, Object> params);
}
