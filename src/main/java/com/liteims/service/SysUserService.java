package com.liteims.service;

import com.liteims.dto.SysUserDTO;
import com.liteims.entity.SysUser;

/**
 * 完成用户模块操作
 */
public interface SysUserService {

    /**
     * 登录
     * @param sysUserDTO 含有账号和密码
     * @return 用户实体
     */
    SysUser login(SysUserDTO sysUserDTO);
}
