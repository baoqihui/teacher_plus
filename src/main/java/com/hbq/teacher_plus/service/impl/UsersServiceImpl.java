package com.hbq.teacher_plus.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.mapper.UsersMapper;
import com.hbq.teacher_plus.model.Users;
import com.hbq.teacher_plus.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 账号信息
 *
 * @author hqb
 * @date 2020-03-10 09:40:54
 */
@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<Users> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<Users> pages = new Page<>(page, limit);
        List<Users> list  =  baseMapper.findList(pages, params);
        return PageResult.<Users>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
