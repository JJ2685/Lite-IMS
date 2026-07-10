package com.liteims.controller;

import com.liteims.common.Result;
import com.liteims.entity.Category;
import com.liteims.entity.Customer;
import com.liteims.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name="用户管理")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    @Operation(summary = "获取客户列表")
    public Result findAll(){
        List<Customer> customers = customerService.list();
        return Result.success(customers);
    }

    @PostMapping
    @Operation(summary = "添加客户")
    public Result add(@RequestBody Customer customer){
        return customerService.save(customer)?Result.success(null):Result.fail("添加失败");
    }

    @PutMapping
    @Operation(summary = "修改客户信息")
    public Result update(@RequestBody Customer customer){
        return customerService.updateById(customer) ? Result.success(null) : Result.fail("修改失败");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "逻辑删除客户")
    public Result delete(@PathVariable Long id){
        return customerService.removeById(id) ? Result.success(null) : Result.fail("删除失败");
    }
}
