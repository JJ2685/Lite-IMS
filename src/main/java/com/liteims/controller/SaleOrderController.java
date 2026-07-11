package com.liteims.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liteims.common.Result;
import com.liteims.dto.OrderDTO;
import com.liteims.entity.SaleOrder;
import com.liteims.entity.SysUser;
import com.liteims.service.SaleOrderService;
import com.liteims.vo.SaleOrderVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name="订单列表")
public class SaleOrderController {
    @Autowired
    SaleOrderService saleOrderService;

    @GetMapping
    @Operation(summary = "获取订单列表")
    private Result<IPage<SaleOrderVO>> findPage(Long page, Long size, String orderNo){
        return Result.success(saleOrderService.findPage(new Page(page,size),orderNo));
    }

    @PostMapping
    @Operation(summary = "创建订单")
    public Result createOrder(@RequestBody OrderDTO orderDTO, HttpSession session){
        SysUser sysUser = (SysUser)session.getAttribute("user");
        orderDTO.setUserId(sysUser.getId());
        saleOrderService.createOrder(orderDTO);
        return Result.success("订单创建成功", null);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新订单状态")
    public Result<Object> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        saleOrderService.updateStatus(id, status);
        return Result.success(null);
    }

}
