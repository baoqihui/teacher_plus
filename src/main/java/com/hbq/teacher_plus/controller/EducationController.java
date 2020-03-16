package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.Education;
import com.hbq.teacher_plus.service.IEducationService;
import com.hbq.teacher_plus.util.ExcelUtil;
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
 * @date 2020-03-16 10:02:22
 */
@Slf4j
@RestController
@Api(tags = "")
public class EducationController {
    @Autowired
    private IEducationService educationService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "cuId", value = "用户id", required = false, dataType = "Integer")
    })
    @GetMapping("/education")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return educationService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/education/{cuId}")
    public Result findUserById(@PathVariable Long cuId) {
        List<Education> list = educationService.list(new QueryWrapper<Education>().eq("cu_id", cuId));
        return Result.succeed(list, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/education")
    public Result save(@RequestBody Education education) {
        educationService.saveOrUpdate(education);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/education/{id}")
    public Result delete(@PathVariable Long id) {
        educationService.removeById(id);
        return Result.succeed("删除成功");
    }
    @ApiOperation(value = "教育信息导入")
    @PostMapping("/education/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<Education> list = ExcelUtil.importExcel(excel, 1, 1, Education.class);
            rowNum = list.size();
            if (rowNum > 0) {
                //无该用户信息
                list.forEach(u -> {
                    u.setCuId(cuId);
                    educationService.save(u);
                });
                return Result.succeed("成功导入信息"+rowNum+"行数据");

            }
        }
        return Result.failed("导入失败");
    }
    @ApiOperation(value = "教育信息导出")
    @PostMapping("/education/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<Education> educations =new ArrayList<>();
        List<Education> educationList = educationService.list(new QueryWrapper<Education>().eq("cu_id", cuId));
        if (educationList.isEmpty()) {educations.add(educationService.getById(0)); } else {
            for (Education education : educationList) {
                educations.add(education);
            }
        }
        //导出操作
        ExcelUtil.exportExcel(educations, "教育信息导出", "教育信息导出", Education.class, "education.xls", response);

    }
}
