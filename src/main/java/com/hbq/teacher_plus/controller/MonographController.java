package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.Monograph;
import com.hbq.teacher_plus.service.IMonographService;
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
 * 教师出版专著情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:58
 */
@Slf4j
@RestController
@Api(tags = "教师出版专著情况")
public class MonographController {
    @Autowired
    private IMonographService monographService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer")
    })
    @GetMapping("/monograph")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return monographService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/monograph/{id}")
    public Result findUserById(@PathVariable Long id) {
        Monograph model = monographService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/monograph")
    public Result save(@RequestBody Monograph monograph) {
        monographService.saveOrUpdate(monograph);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/monograph/{id}")
    public Result delete(@PathVariable Long id) {
        monographService.removeById(id);
        return Result.succeed("删除成功");
    }
    
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/monograph/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<Monograph> list = EasyPoiExcelUtil.importExcel(excel, 1, 1, Monograph.class);
            rowNum = list.size();
            if (rowNum > 0) {
                //无该用户信息
                list.forEach(u -> {
                    u.setCuId(cuId);
                    monographService.save(u);
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
    @PostMapping("/monograph/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<Monograph> monographs =new ArrayList<>();
        List<Monograph> monographList = monographService.list(new QueryWrapper<Monograph>().eq("cu_id", cuId));
        if (monographList.isEmpty()) {monographs.add(monographService.getById(1)); } else {
            for (Monograph monograph : monographList) {
                monographs.add(monograph);
            }
        }
        //导出操作
        EasyPoiExcelUtil.exportExcel(monographs, "教师出版专著情况导出", "教师出版专著情况导出", Monograph.class, "monograph.xls", response);

    }
}
