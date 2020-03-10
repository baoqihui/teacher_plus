package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.Users;
import com.hbq.teacher_plus.common.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 账号信息
 * 
 * @author hqb
 * @date 2020-03-10 09:40:54
 */
@Mapper
public interface UsersMapper extends SuperMapper<Users> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<Users> findList(Page<Users> page, @Param("p") Map<String, Object> params);
}
