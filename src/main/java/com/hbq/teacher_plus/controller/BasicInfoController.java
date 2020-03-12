package com.hbq.teacher_plus.controller;

import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.service.IBasicInfoService;
import com.hbq.teacher_plus.util.UploadImg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/basicInfo/{id}")
    public Result findUserById(@PathVariable Long id) {
        BasicInfo model = basicInfoService.getById(id);
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
}
