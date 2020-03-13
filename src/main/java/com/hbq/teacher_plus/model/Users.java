package com.hbq.teacher_plus.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbq.teacher_plus.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账号信息
 *
 * @author hqb
 * @date 2020-03-10 09:40:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
public class Users extends SuperEntity {
    private static final long serialVersionUID=1L;
        @Excel(name="手机号")
        @ApiModelProperty(value = "手机号")
        private String tel;
        @Excel(name="用户名")
        @ApiModelProperty(value = "用户名")
        private String username;
        @Excel(name="密码")
        @ApiModelProperty(value = "密码")
        private String password;
        @Excel(name="类型")
        @ApiModelProperty(value = "(0：教师 1：管理)")
        private String type;
        @TableLogic
        @ApiModelProperty(value = "逻辑删除",hidden = true)
        private Boolean isDel;
}
