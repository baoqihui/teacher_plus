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

    /*@PostMapping("/users/export")
    public void exportUser(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
        List<SysUserExcel> result = appUserService.findAllUsers(params);
        //导出操作
        ExcelUtil.exportExcel(result, null, "用户", SysUserExcel.class, "user", response);
    }*/

    /*@Override
    public List<SysUserExcel> findAllUsers(Map<String, Object> params) {
        List<SysUserExcel> sysUserExcels = new ArrayList<>();
        List<SysUser> list = baseMapper.findList(new Page<>(1, -1), params);

        for (SysUser sysUser : list) {
            SysUserExcel sysUserExcel = new SysUserExcel();
            BeanUtils.copyProperties(sysUser, sysUserExcel);
            sysUserExcels.add(sysUserExcel);
        }
        return sysUserExcels;
    }*/

    @ApiOperation(value = "基本信息导入")
    @PostMapping("/basicInfo/leadIn")
    public  void leadIn(MultipartFile file,String uid) throws Exception {
        int rowNum = 0;
        if (!file.isEmpty()) {
            List<BasicInfo> list = ExcelUtil.importExcel(file, 0, 1, BasicInfo.class);
            rowNum = list.size();
            if (rowNum > 0) {
                list.forEach(u -> {
                    basicInfoService.save(u);
                });
            }
        }

        BasicInfo existBasicInfo=basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("u_id",uid));
        if (existBasicInfo==null){//无该用户信息


        }else {

        }


    }


}
