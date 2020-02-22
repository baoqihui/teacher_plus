package com.hbq.teacher_plus.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import com.hbq.teacher_plus.common.model.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 基本信息
 *
 * @author hqb
 * @date 2020-02-14 17:18:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("basic_info")
public class BasicInfo extends SuperEntity {
    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "姓名")
        private String name;

        @ApiModelProperty(value = "照片")
        private String imgUrl;

        @ApiModelProperty(value = "工号")
        private String jobNum;

        @ApiModelProperty(value = "性别")
        private String sex;

        @ApiModelProperty(value = "民族")
        private String nation;

        @ApiModelProperty(value = "出生年月")
        private Date birthday;

        @ApiModelProperty(value = "政治面貌")
        private String politicsStatus;

        @ApiModelProperty(value = "证件类型")
        private String certType;

        @ApiModelProperty(value = "证件号码")
        private String certNum;

        @ApiModelProperty(value = "专业技术职务")
        private String skillJob;

        @ApiModelProperty(value = "行政职务")
        private String adminJob;

        @ApiModelProperty(value = "是否外聘")
        private String isExternal;

        @ApiModelProperty(value = "是否青年骨干教师")
        private String isBackbone;

        @ApiModelProperty(value = "是否双师教师")
        private String isDouble;

        @ApiModelProperty(value = "是否创新创业导师")
        private String isInnovate;

        @TableLogic
        @ApiModelProperty(value = "删除标志",hidden = true)
        private Boolean isDel;

}
