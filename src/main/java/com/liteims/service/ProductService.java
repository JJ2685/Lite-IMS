package com.liteims.service;

import com.liteims.entity.Product;
import com.liteims.vo.ProductVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

public interface ProductService extends IService<Product> {

    /**
     * 分页
     * @param page 页码
     * @param size 条数
     * @param name 商品名称关键词
     * @param categoryId 分类主键
     * @return 分页数据
     */
    IPage<ProductVO> findPage(Integer page, Integer size, String name, Long categoryId);
}
