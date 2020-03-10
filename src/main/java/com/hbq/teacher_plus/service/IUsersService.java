package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Users;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 账号信息
 *
 * @author hqb
 * @date 2020-03-10 09:40:54
 */
public interface IUsersService extends IService<Users> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Users> findList(Map<String, Object> params);
}

