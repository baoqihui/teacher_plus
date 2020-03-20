package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.Practical;
import com.hbq.teacher_plus.service.IPracticalService;
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
 * 应用型课程
 *
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
@Slf4j
@RestController
@Api(tags = "应用型课程")
public class PracticalController {
    @Autowired
    private IPracticalService practicalService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "cuId", value = "用户id", required = false, dataType = "Integer")

    })
    @GetMapping("/practical")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return practicalService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/practical/{id}")
    public Result findUserById(@PathVariable Long id) {
        Practical model = practicalService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/practical")
    public Result save(@RequestBody Practical practical) {
        practicalService.saveOrUpdate(practical);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/practical/{id}")
    public Result delete(@PathVariable Long id) {
        practicalService.removeById(id);
        return Result.succeed("删除成功");
    }
    
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/practical/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<Practical> list = EasyPoiExcelUtil.importExcel(excel, 1, 1, Practical.class);
            rowNum = list.size();
            if (rowNum > 0) {
                //无该用户信息
                list.forEach(u -> {
                    u.setCuId(cuId);
                    practicalService.save(u);
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
    @PostMapping("/practical/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<Practical> practicals =new ArrayList<>();
        List<Practical> practicalList = practicalService.list(new QueryWrapper<Practical>().eq("cu_id", cuId));
        if (practicalList.isEmpty()) {practicals.add(practicalService.getById(1)); } else {
            for (Practical practical : practicalList) {
                practicals.add(practical);
            }
        }
        //导出操作
        EasyPoiExcelUtil.exportExcel(practicals, "应用型课程导出", "应用型课程导出", Practical.class, "practical.xls", response);

    }
}
