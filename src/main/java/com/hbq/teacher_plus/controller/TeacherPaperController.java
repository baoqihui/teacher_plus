package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.TeacherPaper;
import com.hbq.teacher_plus.service.ITeacherPaperService;
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
 * 教师发表学术论文情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
@Slf4j
@RestController
@Api(tags = "教师发表学术论文情况")
public class TeacherPaperController {
    @Autowired
    private ITeacherPaperService teacherPaperService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer")
    })
    @GetMapping("/teacherPaper")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return teacherPaperService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/teacherPaper/{id}")
    public Result findUserById(@PathVariable Long id) {
        TeacherPaper model = teacherPaperService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/teacherPaper")
    public Result save(@RequestBody TeacherPaper teacherPaper) {
        teacherPaperService.saveOrUpdate(teacherPaper);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/teacherPaper/{id}")
    public Result delete(@PathVariable Long id) {
        teacherPaperService.removeById(id);
        return Result.succeed("删除成功");
    }
    
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/teacherPaper/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<TeacherPaper> list = EasyPoiExcelUtil.importExcel(excel, 1, 1, TeacherPaper.class);
            rowNum = list.size();
            if (rowNum > 0) {
                //无该用户信息
                list.forEach(u -> {
                    u.setCuId(cuId);
                    teacherPaperService.save(u);
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
    @PostMapping("/teacherPaper/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<TeacherPaper> teacherPapers =new ArrayList<>();
        List<TeacherPaper> teacherPaperList = teacherPaperService.list(new QueryWrapper<TeacherPaper>().eq("cu_id", cuId));
        if (teacherPaperList.isEmpty()) {teacherPapers.add(teacherPaperService.getById(1)); } else {
            for (TeacherPaper teacherPaper : teacherPaperList) {
                teacherPapers.add(teacherPaper);
            }
        }
        //导出操作
        EasyPoiExcelUtil.exportExcel(teacherPapers, "教师发表学术论文情况导出", "教师发表学术论文情况导出", TeacherPaper.class, "teacherPaper.xls", response);

    }
}
