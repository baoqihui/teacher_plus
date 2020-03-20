package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.Patent;
import com.hbq.teacher_plus.service.IPatentService;
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
 * 教师获批专利情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
@Slf4j
@RestController
@Api(tags = "教师获批专利情况")
public class PatentController {
    @Autowired
    private IPatentService patentService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer")
    })
    @GetMapping("/patent")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return patentService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/patent/{id}")
    public Result findUserById(@PathVariable Long id) {
        Patent model = patentService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/patent")
    public Result save(@RequestBody Patent patent) {
        patentService.saveOrUpdate(patent);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/patent/{id}")
    public Result delete(@PathVariable Long id) {
        patentService.removeById(id);
        return Result.succeed("删除成功");
    }
    
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/patent/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<Patent> list = EasyPoiExcelUtil.importExcel(excel, 1, 1, Patent.class);
            rowNum = list.size();
            if (rowNum > 0) {
                //无该用户信息
                list.forEach(u -> {
                    u.setCuId(cuId);
                    patentService.save(u);
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
    @PostMapping("/patent/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<Patent> patents =new ArrayList<>();
        List<Patent> patentList = patentService.list(new QueryWrapper<Patent>().eq("cu_id", cuId));
        if (patentList.isEmpty()) {patents.add(patentService.getById(1)); } else {
            for (Patent patent : patentList) {
                patents.add(patent);
            }
        }
        //导出操作
        EasyPoiExcelUtil.exportExcel(patents, "教师获批专利情况导出", "教师获批专利情况导出", Patent.class, "patent.xls", response);

    }
}
