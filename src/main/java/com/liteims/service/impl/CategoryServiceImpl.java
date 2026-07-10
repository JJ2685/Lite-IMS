package com.liteims.service.impl;

import com.liteims.entity.Category;
import com.liteims.mapper.CategoryMapper;
import com.liteims.service.CategoryService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
