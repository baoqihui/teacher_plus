package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.service.IBasicInfoService;
import com.hbq.teacher_plus.util.ExcelUtil;
import com.hbq.teacher_plus.util.UploadImg;
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
 * 基本信息
 *
 * @author hqb
 * @date 2020-02-14 17:18:15
 */
@Slf4j
@RestController
@Api(tags = "基本信息")
public class BasicInfoController {
    @Autowired
    private IBasicInfoService basicInfoService;

    @RequestMapping(value="/upload.do",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String upload(MultipartFile m_image_addr) throws Exception{
        return UploadImg.imgUpload(m_image_addr);
    }

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer")
    })
    @GetMapping("/basicInfo")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return basicInfoService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/basicInfo/{uid}")
    public Result findUserById(@PathVariable Long uid) {
        BasicInfo model = basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("u_id",uid));
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/basicInfo")
    public Result save(@RequestBody BasicInfo basicInfo) {
        basicInfoService.saveOrUpdate(basicInfo);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/basicInfo/{id}")
    public Result delete(@PathVariable Long id) {
        basicInfoService.removeById(id);
        return Result.succeed("删除成功");
    }

    @ApiOperation(value = "基本信息导入")
    @PostMapping("/basicInfo/leadIn")
    public  Result leadIn(MultipartFile excel,Integer uid) throws Exception {
        System.out.println(uid);
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<BasicInfo> list = ExcelUtil.importExcel(excel, 0, 1, BasicInfo.class);
            rowNum = list.size();
            if (rowNum > 0) {
                BasicInfo existBasicInfo=basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("u_id",uid));
                if (existBasicInfo==null){
                    //无该用户信息
                    list.forEach(u -> {
                        u.setUId(uid);
                        basicInfoService.save(u);
                    });
                    return Result.succeed("成功导入信息"+rowNum+"行数据");
                }else {
                    list.forEach(u -> {
                        u.setId(existBasicInfo.getId());
                        basicInfoService.updateById(u);
                    });
                    return Result.succeed("成功更新信息"+rowNum+"行数据");
                }
            }
        }
        return Result.failed("导入失败");
    }

    @ApiOperation(value = "基本信息导出")
    @PostMapping("/basicInfo/leadOut")
    public void leadOut(Integer uid, HttpServletResponse response) throws IOException {
        List<BasicInfo> basicInfos =new ArrayList<>();
        BasicInfo basicInfo=basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("u_id",uid));
        if (basicInfo==null) {basicInfos.add(basicInfoService.getById(0));} else {basicInfos.add(basicInfo);}
        //导出操作
        ExcelUtil.exportExcel(basicInfos, null, "基本信息导出", BasicInfo.class, "basicInfo", response);

    }
}
