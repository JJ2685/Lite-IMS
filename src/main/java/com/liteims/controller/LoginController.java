package com.liteims.controller;

import com.liteims.common.Result;
import com.liteims.dto.SysUserDTO;
import com.liteims.entity.SysUser;
import com.liteims.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
@Tag(name="登录")
public class LoginController {
    @Autowired
    SysUserService sysUserService;

    @Operation(summary="通过用户名和密码登录")
    @RequestMapping(value="/login",method = POST)
    public Result login(@RequestBody SysUserDTO sysUserDTO) {
        SysUser sysUser=sysUserService.login(sysUserDTO);
        if(sysUser!=null){
            return Result.success(sysUserDTO);
        }else{
            return Result.fail("账号或密码错误");
        }

    }
}
