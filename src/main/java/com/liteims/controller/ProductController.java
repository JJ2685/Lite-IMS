package com.liteims.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liteims.common.Result;
import com.liteims.entity.Customer;
import com.liteims.entity.Product;
import com.liteims.service.ProductService;
import com.liteims.vo.ProductVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name="商品列表")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    @Operation(summary = "分页查询商品列表")
    public  Result<IPage<ProductVO>> findPage(int page, int size, String name, Long categoryId){
        IPage<ProductVO> productVOList=productService.findPage(page,size,name,categoryId);
        return Result.success(productVOList);
    }

    @PostMapping
    @Operation(summary = "添加商品")
    public Result<Object> add(@RequestBody Product product) {
        return productService.save(product) ? Result.success(null) : Result.fail("添加失败");
    }

    @PutMapping
    @Operation(summary = "更新商品")
    public Result<Object> update(@RequestBody Product product) {
        return productService.updateById(product) ? Result.success(null) : Result.fail("更新失败");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品")
    public Result<Object> delete(@PathVariable Long id) {
        return productService.removeById(id) ? Result.success(null) : Result.fail("删除失败");
    }
}
