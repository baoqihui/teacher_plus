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
 * @date 2020-03-18 19:52:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("transaction")
public class Transaction extends SuperEntity {
    private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "用户id")
        private String cuId;
        @Excel(name = "教师姓名")
        @ApiModelProperty(value = "教师姓名")
        private String teacherName;
        @Excel(name = "异动类型")
        @ApiModelProperty(value = "异动类型")
        private String moveType;
        @Excel(name = "开始时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "开始时间")
        private Date startTime;
        @Excel(name = "结束时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "结束时间")
        private Date endTime;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
