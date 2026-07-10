package com.liteims.controller;

import com.liteims.common.Result;
import com.liteims.entity.Category;
import com.liteims.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="分类模块")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "获取所有分类")
    public Result list(){
        List<Category> categories = categoryService.list();  //mp底层IService声明了list()方法
        return Result.success(categories);
    }

    @PostMapping
    @Operation(summary = "添加商品分类")
    public Result add(@RequestBody Category category){
        return categoryService.save(category) ? Result.success(null) : Result.fail("添加失败");
    }

    @PutMapping
    @Operation(summary = "修改商品分类")
    public Result update(@RequestBody Category category){
        return categoryService.updateById(category) ? Result.success(null) : Result.fail("修改失败");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "逻辑删除商品分类")
    public Result delete(@PathVariable Long id){
        return categoryService.removeById(id) ? Result.success(null) : Result.fail("删除失败");
    }
}
