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
 * 学生论文
 *
 * @author hqb
 * @date 2020-03-16 22:29:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("paper")
public class Paper extends SuperEntity {
    private static final long serialVersionUID=1L;

            @ApiModelProperty(value = "用户id")
            private String cuId;
            @Excel(name = "论文名称")
            @ApiModelProperty(value = "论文名称")
            private String paperTitle;
            @Excel(name = "期刊")
            @ApiModelProperty(value = "期刊")
            private String periodical;
            @Excel(name = "日期",format="yyyy-MM-dd HH:mm:ss")
            @ApiModelProperty(value = "日期")
            private Date publishTime;
            @Excel(name = "级别")
            @ApiModelProperty(value = "级别")
            private String rank;
            @Excel(name = "学生姓名")
            @ApiModelProperty(value = "学生姓名")
            private String stuName;
            @Excel(name = "第一作者")
            @ApiModelProperty(value = "第一作者")
            private String  firstAuthor;
            @Excel(name = "学生名次")
            @ApiModelProperty(value = "学生名次")
            private String stuRank;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
