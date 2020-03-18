package com.hbq.teacher_plus.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbq.teacher_plus.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author hqb
 * @date 2020-03-16 17:24:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("teacher_info")
public class TeacherInfo extends SuperEntity {
    private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "用户id")
        private String cuId;
        @Excel(name = "学期时间")
        @ApiModelProperty(value = "学期时间")
        private String termTime;
        @Excel(name = "课程编号")
        @ApiModelProperty(value = "课程编号")
        private String courseNum;
        @Excel(name = "课程名")
        @ApiModelProperty(value = "课程名")
        private String courseName;
        @Excel(name = "课程性质")
        @ApiModelProperty(value = "课程性质")
        private String courseNature;
        @Excel(name = "理论课时")
        @ApiModelProperty(value = "理论课时")
        private String theory;
        @Excel(name = "实验学时")
        @ApiModelProperty(value = "实验学时")
        private String experimental;
        @Excel(name = "实训学时")
        @ApiModelProperty(value = "实训学时")
        private String training;
        @Excel(name = "是否优质课")
        @ApiModelProperty(value = "是否优质课")
        private String qualityClass;
        @Excel(name = "本人承担实际学时")
        @ApiModelProperty(value = "本人承担实际学时")
        private String persion;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;

        private String name;
}
