package com.hbq.teacher_plus.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbq.teacher_plus.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

        @ApiModelProperty(value = "用户id")
        private String cuId;

        @Excel(name = "姓名")
        @ApiModelProperty(value = "姓名")
        private String name;

        @ApiModelProperty(value = "照片")
        private String imgUrl;

        @Excel(name = "工号")
        @ApiModelProperty(value = "工号")
        private String jobNum;

        @Excel(name = "性别")
        @ApiModelProperty(value = "性别")
        private String sex;

        @Excel(name = "民族")
        @ApiModelProperty(value = "民族")
        private String nation;

        @Excel(name = "出生年月",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "出生年月")
        private Date birthday;

        @Excel(name = "政治面貌")
        @ApiModelProperty(value = "政治面貌")
        private String politicsStatus;

        @Excel(name = "证件类型")
        @ApiModelProperty(value = "证件类型")
        private String certType;

        @Excel(name = "证件号码")
        @ApiModelProperty(value = "证件号码")
        private String certNum;

        @Excel(name = "专业技术职务")
        @ApiModelProperty(value = "专业技术职务")
        private String skillJob;

        @Excel(name = "行政职务")
        @ApiModelProperty(value = "行政职务")
        private String adminJob;

        @Excel(name = "是否外聘")
        @ApiModelProperty(value = "是否外聘")
        private String isExternal;

        @Excel(name = "是否青年骨干教师")
        @ApiModelProperty(value = "是否青年骨干教师")
        private String isBackbone;

        @Excel(name = "是否双师教师")
        @ApiModelProperty(value = "是否双师教师")
        private String isDouble;

        @Excel(name = "是否创新创业导师")
        @ApiModelProperty(value = "是否创新创业导师")
        private String isInnovate;

        @TableLogic
        @ApiModelProperty(value = "删除标志",hidden = true)
        private Boolean isDel;

}
