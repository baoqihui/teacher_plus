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
 * 教师发表学术论文情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("teacher_paper")
public class TeacherPaper extends SuperEntity {
    private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "用户id")
        private String cuId;
        @Excel(name = "论文名称")
        @ApiModelProperty(value = "论文名称")
        private String paperName;
        @Excel(name = "第一作者或通讯作者")
        @ApiModelProperty(value = "第一作者或通讯作者")
        private String author;
        @Excel(name = "发表期刊")
        @ApiModelProperty(value = "发表期刊")
        private String published;
        @Excel(name = "发表时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "发表时间")
        private Date publishTime;
        @Excel(name = "收录情况")
        @ApiModelProperty(value = "收录情况")
        private String record;
        @Excel(name = "他引情况")
        @ApiModelProperty(value = "他引情况")
        private String citation;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
