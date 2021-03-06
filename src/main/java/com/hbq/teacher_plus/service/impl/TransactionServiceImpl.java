package com.hbq.teacher_plus.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.mapper.TransactionMapper;
import com.hbq.teacher_plus.model.Transaction;
import com.hbq.teacher_plus.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-18 19:52:36
 */
@Slf4j
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {
    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<Transaction> findList(Map<String, Object> params){
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = -1;
        }
        Page<Transaction> pages = new Page<>(page, limit);
        List<Transaction> list  =  baseMapper.findList(pages, params);
        return PageResult.<Transaction>builder().data(list).code(0).count(pages.getTotal()).build();
    }
}
