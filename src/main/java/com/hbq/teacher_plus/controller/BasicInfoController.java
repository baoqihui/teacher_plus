package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.model.Education;
import com.hbq.teacher_plus.model.Work;
import com.hbq.teacher_plus.service.IBasicInfoService;
import com.hbq.teacher_plus.service.IEducationService;
import com.hbq.teacher_plus.service.IWorkService;
import com.hbq.teacher_plus.util.BuildPath;
import com.hbq.teacher_plus.util.ExcelUtil;
import com.hbq.teacher_plus.util.ExcelUtils;
import com.hbq.teacher_plus.util.UploadImg;
import com.hbq.teacher_plus.vo.BasicInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private IEducationService educationService;
    @Autowired
    private IWorkService workService;
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
    @GetMapping("/basicInfo/{cuId}")
    public Result findUserById(@PathVariable String cuId) {
        BasicInfo model = basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("cu_id",cuId));
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/basicInfo")
    public Result save(@RequestBody BasicInfo basicInfo) {
        BasicInfo existBasicInfo=basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("cu_id",basicInfo.getCuId()));
        if (existBasicInfo==null){
            basicInfoService.save(basicInfo);
        }else {
           basicInfoService.update(basicInfo,new QueryWrapper<BasicInfo>().eq("cu_id",basicInfo.getCuId()));
        }
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
    @ApiOperation(value = "通过cu_id删除")
    @DeleteMapping("/basicInfo/byCuId/{cuId}")
    public Result deleteByCuId(@PathVariable Long cuId) {
        basicInfoService.remove(new QueryWrapper<BasicInfo>().eq("cu_id",cuId));
        return Result.succeed("删除成功");
    }
    @ApiOperation(value = "基本信息导入")
    @PostMapping("/basicInfo/leadIn")
    public  Result leadIn(MultipartFile excel,String cuId) throws Exception {
        int rowNum = 0;
        if (!excel.isEmpty()) {
            List<BasicInfo> list = ExcelUtil.importExcel(excel, 1, 1, BasicInfo.class);
            rowNum = list.size();
            if (rowNum > 0) {
                BasicInfo existBasicInfo=basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("cu_id",cuId));
                if (existBasicInfo==null){
                    //无该用户信息
                    list.forEach(u -> {
                        u.setCuId(cuId);
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
    public void leadOut(String cuId, HttpServletResponse response) throws IOException {
        List<BasicInfo> basicInfos =new ArrayList<>();
        BasicInfo basicInfo=basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("cu_id",cuId));
        if (basicInfo==null) {basicInfos.add(basicInfoService.getById(0));} else {basicInfos.add(basicInfo);}
        //导出操作
        ExcelUtil.exportExcel(basicInfos, "基本信息导出", "基本信息导出", BasicInfo.class, "basicInfo.xls", response);

    }
    @ApiOperation(value = "基本信息导出")
    @PostMapping("/basicInfo/leadOutAll")
    public void leadOutAll(String cuId, HttpServletResponse response) throws Exception {

        Map<String, Object> params=new HashMap<>();
        String[] titles={"姓名","证件类型","证件号码","性别","出生年月",
                "专业技术职务","最终学位",
                "学位","所学专业","取得学位时间","毕业学校",
                "学位","所学专业","取得学位时间","毕业学校",
                "参加工作时间","任教时间","到本专业任教时间",
                "是否有行业经历","中青年教师实践教学能力培训","研究生导师"
        };

        Workbook wk= ExcelUtils.exportExcel(titles,"全部基本信息导出");

        XSSFSheet sheet=(XSSFSheet) wk.getSheet("全部基本信息导出");
        List<BasicInfo> data = basicInfoService.list();
        List<BasicInfoVo> basicInfoVos=new ArrayList<>();
        List<Map<String,Object>> mapArrayList=new ArrayList<>();
        XSSFRow row = sheet.createRow(data.size()+1);
        for (BasicInfo basicInfo : data) {
            Map<String,Object> map=new HashMap<>();
            System.out.println(basicInfo);
            cuId=basicInfo.getCuId();
            Work work = workService.getOne(new QueryWrapper<Work>().eq("cu_id", cuId));
            List<Education> educations = educationService.list(new QueryWrapper<Education>().eq("cu_id", cuId));
            map.put("basicInfo",basicInfo);
            map.put("work",work);
            map.put("educations",educations);
            mapArrayList.add(map);



            row.createCell(0).setCellValue(basicInfo.getName());
            row.createCell(1).setCellValue(basicInfo.getCertType());
            row.createCell(2).setCellValue(basicInfo.getCertNum());
            row.createCell(3).setCellValue(basicInfo.getSex());

           /* row.createCell(4).setCellValue(doctors.get(i).getD_tel());
            row.createCell(5).setCellValue(doctors.get(i).getD_plane());
            row.createCell(6).setCellValue(doctors.get(i).getD_sex());
            row.createCell(7).setCellValue(doctors.get(i).getD_age());
            row.createCell(8).setCellValue(doctors.get(i).getD_date());

            row.createCell(9).setCellValue(doctors.get(i).getD_mail());
            row.createCell(10).setCellValue(doctors.get(i).getDepartment().getDe_name());
            row.createCell(11).setCellValue(doctors.get(i).getD_educ());
            row.createCell(12).setCellValue(doctors.get(i).getD_remark());*/

        }

        OutputStream out= BuildPath.Manual_Saving(response, "Doctor.xls");
        wk.write(out);
        wk.close();

    }
    /**
     * 查询用户信息（全）
     * */
    @ApiOperation(value = "查询")
    @GetMapping("/basicInfo/findAllUsers")
    public Result findAllUsers(@RequestParam(required = false) Map<String, Object> params) {
        PageResult<BasicInfoVo> basicInfos = basicInfoService.findList2(params);
        List<BasicInfoVo> list=basicInfos.getData();
        for (BasicInfoVo basicInfo : list) {
            String cuId=basicInfo.getCuId();
            basicInfo.setEducations(educationService.list(new QueryWrapper<Education>().eq("cu_id",cuId).orderByAsc("cert_id")));
        }
        return Result.succeed(basicInfos, "查询成功");
    }

}
