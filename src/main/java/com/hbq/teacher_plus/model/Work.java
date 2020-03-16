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
 * 工作经历
 *
 * @author hqb
 * @date 2020-03-16 16:22:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("work")
public class Work extends SuperEntity {
    private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "用户id")
        private String cuId;
        @Excel(name = "非学历进修")
        @ApiModelProperty(value = "非学历进修")
        private String noStudy;
        @Excel(name = "参加工作时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "参加工作时间")
        private Date workTime;
        @Excel(name = "任教时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "任教时间")
        private Date teachTime;
        @Excel(name = "到本专业任教时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "到本专业任教时间")
        private Date atTime;
        @Excel(name = "是否有行业经历")
        @ApiModelProperty(value = "是否有行业经历")
        private String isWork;
        @Excel(name = "中青年教师实践能力培训")
        @ApiModelProperty(value = "中青年教师实践能力培训")
        private String train;
        @Excel(name = "研究生导师")
        @ApiModelProperty(value = "研究生导师")
        private String supervisor;
        @Excel(name = "人才入选情况")
        @ApiModelProperty(value = "人才入选情况")
        private String talents;
        @Excel(name = "行业经历")
        @ApiModelProperty(value = "行业经历")
        private String experience;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
