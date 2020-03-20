package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.Work;
import com.hbq.teacher_plus.service.IWorkService;
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
 * 工作经历
 *
 * @author hqb
 * @date 2020-03-16 16:22:02
 */
@Slf4j
@RestController
@Api(tags = "工作经历")
public class WorkController {
    @Autowired
    private IWorkService workService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer")
    })
    @GetMapping("/work")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return workService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/work/{cuId}")
    public Result findUserById(@PathVariable String cuId) {
        Work model = workService.getOne(new QueryWrapper<Work>().eq("cu_id",cuId));
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/work")
    public Result save(@RequestBody Work work) {
        Work existWork=workService.getOne(new QueryWrapper<Work>().eq("cu_id",work.getCuId()));
        if (existWork==null){
            workService.save(work);
        }else {
            workService.update(work,new QueryWrapper<Work>().eq("cu_id",work.getCuId()));
        }
        return Result.succeed("保存成功");

    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/work/{id}")
    public Result delete(@PathVariable Long id) {
        workService.removeById(id);
        return Result.succeed("删除成功");
    }
    
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/work/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<Work> list = EasyPoiExcelUtil.importExcel(excel, 1, 1, Work.class);
            rowNum = list.size();
            if (rowNum > 0) {
                Work existWork=workService.getOne(new QueryWrapper<Work>().eq("cu_id",cuId));
                if (existWork==null){
                    //无该用户信息
                    list.forEach(u -> {
                        u.setCuId(cuId);
                        workService.save(u);
                    });
                    return Result.succeed("成功导入信息"+rowNum+"行数据");
                }else {
                    list.forEach(u -> {
                        u.setId(existWork.getId());
                        workService.updateById(u);
                    });
                    return Result.succeed("成功更新信息"+rowNum+"行数据");
                }
            }
        }
        return Result.failed("导入失败");
    }
    
    /**
     * 导出
     */
    @ApiOperation(value = "导出")
    @PostMapping("/work/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<Work> works =new ArrayList<>();
        List<Work> workList = workService.list(new QueryWrapper<Work>().eq("cu_id", cuId));
        if (workList.isEmpty()) {works.add(workService.getById(1)); } else {
            for (Work work : workList) {
                works.add(work);
            }
        }
        //导出操作
        EasyPoiExcelUtil.exportExcel(works, "工作经历导出", "工作经历导出", Work.class, "work.xls", response);

    }
}
