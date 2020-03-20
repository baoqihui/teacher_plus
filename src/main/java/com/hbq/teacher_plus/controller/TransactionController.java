package com.hbq.teacher_plus.controller;

import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.Transaction;
import com.hbq.teacher_plus.service.ITransactionService;
import com.hbq.teacher_plus.util.EasyPoiExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-18 19:52:36
 */
@Slf4j
@RestController
@Api(tags = "教师异动情况")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer")
    })
    @GetMapping("/transaction")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return transactionService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/transaction/{id}")
    public Result findUserById(@PathVariable Long id) {
        Transaction model = transactionService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/transaction")
    public Result save(@RequestBody Transaction transaction) {
        transactionService.saveOrUpdate(transaction);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/transaction/{id}")
    public Result delete(@PathVariable Long id) {
        transactionService.removeById(id);
        return Result.succeed("删除成功");
    }
    
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/transaction/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<Transaction> list = EasyPoiExcelUtil.importExcel(excel, 1, 1, Transaction.class);
            rowNum = list.size();
            if (rowNum > 0) {
                    //无该用户信息
                    list.forEach(u -> {
                        u.setCuId(cuId);
                        transactionService.save(u);
                    });
                    return Result.succeed("成功导入信息"+rowNum+"行数据");
            }
        }
        return Result.failed("导入失败");
    }
    
    /**
     * 导出
     */
    @ApiOperation(value = "导出")
    @PostMapping("/transaction/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<Transaction> transactions =new ArrayList<>();
        List<Transaction> transactionList = transactionService.list();
        if (transactionList.isEmpty()) {transactions.add(transactionService.getById(1)); } else {
            for (Transaction transaction : transactionList) {
                transactions.add(transaction);
            }
        }
        //导出操作
        EasyPoiExcelUtil.exportExcel(transactions, "教师异动情况导出", "教师异动情况导出", Transaction.class, "transaction.xls", response);

    }
}
