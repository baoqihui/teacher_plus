package com.hbq.teacher_plus.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.model.Education;
import com.hbq.teacher_plus.model.Work;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class BasicInfoVo extends BasicInfo {
    @Excel(name = "教育信息")
    @ApiModelProperty(value = "教育信息",hidden = true)
    private List<Education> educations;
    @Excel(name = "工作信息")
    @ApiModelProperty(value = "工作信息",hidden = true)
    private Work work;
}
