package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.TeacherInfo;
import com.hbq.teacher_plus.service.ITeacherInfoService;
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
 * @date 2020-03-16 17:24:27
 */
@Slf4j
@RestController
@Api(tags = "教学内容")
public class TeacherInfoController {
    @Autowired
    private ITeacherInfoService teacherInfoService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "cuId", value = "用户id", required = false, dataType = "Integer")
    })
    @GetMapping("/teacherInfo")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return teacherInfoService.findList(params);
    }

    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @GetMapping("/teacherInfo/findAllUsers")
    public PageResult findAllUsers(@RequestParam(required = false) Map<String, Object> params) {
        return teacherInfoService.findList2(params);
    }
    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/teacherInfo/{id}")
    public Result findUserById(@PathVariable Long id) {
        TeacherInfo model = teacherInfoService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/teacherInfo")
    public Result save(@RequestBody TeacherInfo teacherInfo) {
        teacherInfoService.saveOrUpdate(teacherInfo);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/teacherInfo/{id}")
    public Result delete(@PathVariable Long id) {
        teacherInfoService.removeById(id);
        return Result.succeed("删除成功");
    }
    @ApiOperation(value = "通过cu_id删除")
    @DeleteMapping("/teacherInfo/byCuId/{cuId}/{cid}")
    public Result deleteByCuId(@PathVariable Long cuId,@PathVariable Long cid) {
        teacherInfoService.remove(new QueryWrapper<TeacherInfo>().eq("cu_id",cuId).eq("course_num",cid));
        return Result.succeed("删除成功");
    }
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/teacherInfo/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<TeacherInfo> list = ExcelUtil.importExcel(excel, 1, 1, TeacherInfo.class);
            rowNum = list.size();
            if (rowNum > 0) {
                list.forEach(u -> {
                    u.setCuId(cuId);
                    teacherInfoService.save(u);
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
    @PostMapping("/teacherInfo/leadOutAll")
    public void leadOutAll(String cuId, HttpServletResponse response) throws IOException {
        List<TeacherInfo> teacherInfos =new ArrayList<>();
        List<TeacherInfo> teacherInfoList = teacherInfoService.list();
        if (teacherInfoList.isEmpty()) {teacherInfos.add(teacherInfoService.getById(0)); } else {
            for (TeacherInfo teacherInfo : teacherInfoList) {
                teacherInfos.add(teacherInfo);
            }
        }
        //导出操作
        ExcelUtil.exportExcel(teacherInfos, "教学内容导出", "教学内容导出", TeacherInfo.class, "teacherInfo.xls", response);

    }
    /**
     * 导出
     */
    @ApiOperation(value = "导出全部")
    @PostMapping("/teacherInfo/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<TeacherInfo> teacherInfos =new ArrayList<>();
        List<TeacherInfo> teacherInfoList = teacherInfoService.list(new QueryWrapper<TeacherInfo>().eq("cu_id", cuId));
        if (teacherInfoList.isEmpty()) {teacherInfos.add(teacherInfoService.getById(1)); } else {
            for (TeacherInfo teacherInfo : teacherInfoList) {
                teacherInfos.add(teacherInfo);
            }
        }
        //导出操作
        ExcelUtil.exportExcel(teacherInfos, "教学内容导出", "教学内容导出", TeacherInfo.class, "teacherInfo.xls", response);

    }
}
