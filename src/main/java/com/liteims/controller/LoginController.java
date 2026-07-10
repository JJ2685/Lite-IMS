package com.liteims.controller;

import com.liteims.common.Result;
import com.liteims.dto.SysUserDTO;
import com.liteims.entity.SysUser;
import com.liteims.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;

@RestController
@RequestMapping("/api")
@Tag(name="登录")
public class LoginController implements HandlerInterceptor {
    @Autowired
    SysUserService sysUserService;

    @Operation(summary="通过用户名和密码登录")
    @PostMapping(value="/login")
    public Result login(@RequestBody SysUserDTO sysUserDTO,HttpSession session) {
        SysUser sysUser=sysUserService.login(sysUserDTO);
        if(sysUser!=null){
            session.setAttribute("sysUser",sysUser);
            return Result.success(sysUser);
        }else{
            return Result.fail("账号或密码错误");
        }

    }

    @Operation(summary="注销")
    @GetMapping(value="/logout")
    public Result logout(HttpSession session) {
        if(session.getAttribute("sysUser")!=null){  //防止空指针异常
            session.removeAttribute("sysUser");
            session.invalidate();
        }
        return Result.success(null);
    }
}
