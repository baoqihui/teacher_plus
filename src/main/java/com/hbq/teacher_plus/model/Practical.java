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
 * 应用型课程
 *
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("practical")
public class Practical extends SuperEntity {
    private static final long serialVersionUID=1L;
            @ApiModelProperty(value = "用户id")
            private String cuId;
            @Excel(name = "课程名称")
            @ApiModelProperty(value = "课程名称")
            private String courseTitle;
            @Excel(name = "立项时间",format="yyyy-MM-dd HH:mm:ss")
            @ApiModelProperty(value = "立项时间")
            private Date projectTime;
            @Excel(name = "是否结项")
            @ApiModelProperty(value = "是否结项")
            private String isPost;
            @Excel(name = "参与情况")
            @ApiModelProperty(value = "参与情况")
            private String participation;
            @Excel(name = "经费（万）")
            @ApiModelProperty(value = "经费（万）")
            private String expenditure;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
