package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.Paper;
import com.hbq.teacher_plus.service.IPaperService;
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
 * 学生论文
 *
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
@Slf4j
@RestController
@Api(tags = "学生论文")
public class PaperController {
    @Autowired
    private IPaperService paperService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "cuId", value = "用户id", required = false, dataType = "Integer")

    })
    @GetMapping("/paper")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return paperService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/paper/{id}")
    public Result findUserById(@PathVariable Long id) {
        Paper model = paperService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/paper")
    public Result save(@RequestBody Paper paper) {
        paperService.saveOrUpdate(paper);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/paper/{id}")
    public Result delete(@PathVariable Long id) {
        paperService.removeById(id);
        return Result.succeed("删除成功");
    }
    
    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/paper/leadIn")
    public  Result leadIn(MultipartFile excel, String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<Paper> list = EasyPoiExcelUtil.importExcel(excel, 1, 1, Paper.class);
            rowNum = list.size();
            if (rowNum > 0) {
                //无该用户信息
                list.forEach(u -> {
                    u.setCuId(cuId);
                    paperService.save(u);
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
    @PostMapping("/paper/leadOut")
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<Paper> papers =new ArrayList<>();
        List<Paper> paperList = paperService.list(new QueryWrapper<Paper>().eq("cu_id", cuId));
        if (paperList.isEmpty()) {papers.add(paperService.getById(1)); } else {
            for (Paper paper : paperList) {
                papers.add(paper);
            }
        }
        //导出操作
        EasyPoiExcelUtil.exportExcel(papers, "学生论文导出", "学生论文导出", Paper.class, "paper.xls", response);

    }
}
