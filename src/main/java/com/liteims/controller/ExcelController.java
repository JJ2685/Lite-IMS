package com.liteims.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liteims.entity.Customer;
import com.liteims.entity.SaleOrder;
import com.liteims.mapper.CustomerMapper;
import com.liteims.mapper.SaleOrderMapper;
import com.liteims.vo.OrderExportVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/excel")
@Tag(name = "数据导出")
public class ExcelController {
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/orders/export")
    @Operation(summary = "导出订单报表")
    public void exportOrders(HttpServletResponse response) throws IOException {
        // 1. 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("销售订单报表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        // 2. 查询数据
        List<SaleOrder> orders = saleOrderMapper.selectList(new QueryWrapper<SaleOrder>().orderByDesc("create_time"));

        // 批量查询客户信息，避免循环查库 (性能优化)
        //.stream()数据流操作
        List<Long> customerIds = orders.stream().map(SaleOrder::getCustomerId).distinct().collect(Collectors.toList());
        Map<Long, String> customerMap = customerIds.isEmpty() ? Map.of() :   //Map.of()：创建空map集合
                customerMapper.selectBatchIds(customerIds).stream()         //selectBatchIds（）：WHERE id IN (customerIds)
                        .collect(Collectors.toMap(Customer::getId, Customer::getName));

        // 3. 转换为导出对象
        List<OrderExportVO> exportList = new ArrayList<>();
        for (SaleOrder order : orders) {
            OrderExportVO vo = new OrderExportVO();
            vo.setOrderNo(order.getOrderNo());
            vo.setCustomerName(customerMap.getOrDefault(order.getCustomerId(), "未知客户"));
            vo.setTotalAmount(order.getTotalAmount());
            vo.setCreateTime(order.getCreateTime());

            // 状态转换
            String statusStr = switch (order.getStatus()) {
                case 0 -> "待支付";
                case 1 -> "已支付";
                case 2 -> "已发货";
                case 3 -> "已完成";
                case 4 -> "已取消";
                default -> "待处理";
            };
            vo.setStatusStr(statusStr);

            exportList.add(vo);
        }

        // 4. 写出 Excel
        EasyExcel.write(response.getOutputStream(), OrderExportVO.class)
                .sheet("订单列表")
                .doWrite(exportList);
    }
}
