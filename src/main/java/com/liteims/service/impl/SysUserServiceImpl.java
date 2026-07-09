package com.liteims.service.impl;

import com.liteims.dto.SysUserDTO;
import com.liteims.entity.SysUser;
import com.liteims.mapper.SysUserMapper;
import com.liteims.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser login(SysUserDTO sysUserDTO) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
        queryWrapper.eq(SysUser::getUsername, sysUserDTO.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }
}
