package com.hbq.teacher_plus.service;

import com.hbq.teacher_plus.model.Transaction;
import com.hbq.teacher_plus.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-18 19:52:36
 */
public interface ITransactionService extends IService<Transaction> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Transaction> findList(Map<String, Object> params);
}

