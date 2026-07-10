package com.liteims.service.impl;

import com.liteims.entity.Product;
import com.liteims.mapper.ProductMapper;
import com.liteims.service.ProductService;
import com.liteims.vo.ProductVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public IPage<ProductVO> findPage(Integer page, Integer size, String name, Long categoryId) {
        return productMapper.findPage(new Page<>(page, size),name,categoryId);
    }
}
