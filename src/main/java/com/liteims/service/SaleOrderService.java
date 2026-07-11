package com.liteims.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liteims.dto.OrderDTO;
import com.liteims.entity.SaleOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;
import com.liteims.vo.SaleOrderVO;

public interface SaleOrderService extends IService<SaleOrder> {

    IPage<SaleOrderVO> findPage(Page page, String orderNo);

    void createOrder(OrderDTO orderDTO);

    void updateStatus(Long id, Integer status);
}
