package com.hbq.teacher_plus.mapper;

import com.hbq.teacher_plus.model.Transaction;
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
 * @date 2020-03-18 19:52:36
 */
@Mapper
public interface TransactionMapper extends SuperMapper<Transaction> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<Transaction> findList(Page<Transaction> page, @Param("p") Map<String, Object> params);
}
