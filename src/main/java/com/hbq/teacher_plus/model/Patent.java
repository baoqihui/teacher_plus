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
 * 教师获批专利情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("patent")
public class Patent extends SuperEntity {
    private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "用户id")
        private String cuId;
        @Excel(name = "专利名称")
        @ApiModelProperty(value = "专利名称")
        private String patentName;
        @Excel(name = "作者")
        @ApiModelProperty(value = "作者")
        private String patentAuthor;
        @Excel(name = "最高排名")
        @ApiModelProperty(value = "最高排名")
        private String highestRanking;
        @Excel(name = "专利类型")
        @ApiModelProperty(value = "专利类型")
        private String type;
        @Excel(name = "获批时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "获批时间")
        private Date approvedTime;
        @Excel(name = "是否应用")
        @ApiModelProperty(value = "是否应用")
        private String isApplication;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
