package com.hbq.teacher_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbq.teacher_plus.common.model.PageResult;
import com.hbq.teacher_plus.common.model.Result;
import com.hbq.teacher_plus.model.BasicInfo;
import com.hbq.teacher_plus.model.Users;
import com.hbq.teacher_plus.service.IBasicInfoService;
import com.hbq.teacher_plus.service.IUsersService;
import com.hbq.teacher_plus.util.EasyPoiExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 账号信息
 *
 * @author hqb
 * @date 2020-03-10 09:40:54
 */
@Slf4j
@RestController
@Api(tags = "账号信息")
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private IBasicInfoService basicInfoService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表(此接口请使用PostMan测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "当前页数量", required = false, dataType = "Integer")
    })
    @GetMapping("/users")
    public PageResult list(@RequestParam(required = false) Map<String, Object> params) {
        return usersService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/users/{id}")
    public Result findUserById(@PathVariable Long id) {
        Users model = usersService.getById(id);
        return Result.succeed(model, "查询成功");
    }
    /**
     * 查询
     */
    @ApiOperation(value = "通过手机号查询用户姓名")
    @GetMapping("/users/findUserNameByTel/{tel}")
    public Result findUserNameByTel(@PathVariable String tel) {
        Users model = usersService.getOne(new QueryWrapper<Users>().eq("tel",tel).eq("is_del",0));
        BasicInfo one = basicInfoService.getOne(new QueryWrapper<BasicInfo>().eq("cu_id", model.getId()).eq("is_del", 0));
        return Result.succeed(one.getName(), "查询成功");
    }
    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/users")
    public Result save(Users users,String codes,HttpSession session) {
        String tel=(String) session.getAttribute("tel");
        if(tel==null){
            return  Result.failed("验证码失效！");
        }
        String code=(String) session.getAttribute("code");
        if(tel.equals(users.getTel())&&code.equals(codes)){
            Users existUsers = usersService.getOne(new QueryWrapper<Users>().eq("tel", users.getTel()).eq("is_del",0));
            if (existUsers!=null){
                existUsers.setPassword(users.getPassword());
                usersService.saveOrUpdate(existUsers);
                return Result.succeed("修改密码成功");
            }else{
                users.setUsername("tch"+users.getTel());
                users.setIsDel(false);
                users.setType("0");
                usersService.saveOrUpdate(users);
                return Result.succeed("注册成功");
            }
        }
        return Result.failed("操作失败,验证码有误");
    }

    /**
     * 通过手机号查询用户信息
     * */
    @ApiOperation(value = "通过手机号查询用户信息")
    @GetMapping("/users/getUsersInfo/{tel}")
    public Result findUserByTel(@PathVariable String tel) {
        Users model = usersService.getOne(new QueryWrapper<Users>().eq("tel",tel));
        return Result.succeed(model, "查询成功");
    }
    /**
     * 通过手机号修改用户信息
     * */
    @ApiOperation(value = "修改用户权限")
    @PostMapping(value="/users/editUsersInfo")
    @ResponseBody
    public Result editUserByTel(String tel,String type) {
        Users model = usersService.getOne(new QueryWrapper<Users>().eq("tel",tel));
        model.setType(type);
        usersService.updateById(model);
        return Result.succeed(model, "修改权限成功");
    }
    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/users/{id}")
    public Result delete(@PathVariable Long id) {
        usersService.removeById(id);
        return Result.succeed("删除成功");
    }
    @PostMapping(value = "/users/import")
    public Result importExcl(@RequestParam("file") MultipartFile excl) throws Exception {
        int rowNum = 0;
        if (!excl.isEmpty()) {
            List<Users> list = EasyPoiExcelUtil.importExcel(excl, 0, 1, Users.class);
            rowNum = list.size();
            if (rowNum > 0) {
                list.forEach(u -> {
                    usersService.save(u);
                });
            }
        }
        return Result.succeed("导入数据成功，一共【" + rowNum + "】行");
    }
}
