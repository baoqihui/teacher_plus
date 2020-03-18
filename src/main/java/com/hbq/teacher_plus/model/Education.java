package com.hbq.teacher_plus.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import com.hbq.teacher_plus.common.model.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-16 10:02:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("education")
public class Education extends SuperEntity {
    private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "用户id")
        private String cuId;
        @ApiModelProperty(value = "学历id")
        private Integer certId;
        @Excel(name = "学历")
        @ApiModelProperty(value = "学历")
        private String certificate;
        @Excel(name = "就读学校")
        @ApiModelProperty(value = "就读学校")
        private String school;
        @Excel(name = "所学专业")
        @ApiModelProperty(value = "所学专业")
        private String major;
        @Excel(name = "就读时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "就读时间")
        private Date studyingTime;
        @Excel(name = "毕业时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "毕业时间")
        private Date graduaTime;
        @Excel(name = "学位")
        @ApiModelProperty(value = "学位")
        private String degree;
        @Excel(name = "性质")
        @ApiModelProperty(value = "性质")
        private String nature;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
