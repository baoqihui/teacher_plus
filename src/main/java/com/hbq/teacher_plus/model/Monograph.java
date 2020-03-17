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
 * 教师出版专著情况
 *
 * @author hqb
 * @date 2020-03-17 09:29:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("monograph")
public class Monograph extends SuperEntity {
    private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "用户id")
        private String cuId;
        @Excel(name = "专著名称")
        @ApiModelProperty(value = "专著名称")
        private String monographName;
        @Excel(name = "第一著作人")
        @ApiModelProperty(value = "第一著作人")
        private String author;
        @Excel(name = "专著类别")
        @ApiModelProperty(value = "专著类别")
        private String type;
        @Excel(name = "出版社")
        @ApiModelProperty(value = "出版社")
        private String press;
        @Excel(name = "出版时间",format="yyyy-MM-dd HH:mm:ss")
        @ApiModelProperty(value = "出版时间")
        private Date pressTime;
        @Excel(name = "主编/参编")
        @ApiModelProperty(value = "主编/参编")
        private String chiefEditor;
        @TableLogic
        @ApiModelProperty(value = "",hidden = true)
        private Boolean isDel;
}
