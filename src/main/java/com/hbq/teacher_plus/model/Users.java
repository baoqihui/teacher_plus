package com.hbq.teacher_plus.model;

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

        @ApiModelProperty(value = "手机号")
        private String tel;
        @ApiModelProperty(value = "用户名")
        private String username;
        @ApiModelProperty(value = "密码")
        private String password;
        @ApiModelProperty(value = "(0：教师 1：管理)")
        private String type;
        @TableLogic
        @ApiModelProperty(value = "逻辑删除",hidden = true)
        private Boolean isDel;
}
