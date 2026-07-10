package com.liteims.controller;

import com.liteims.common.Result;
import com.liteims.service.DashboardService;
import com.liteims.vo.DashboardVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name="仪表盘：获取统计数据")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/stats")
    public Result getStats() {
        DashboardVO dashboardVO = dashboardService.getStats();
        return Result.success(dashboardVO);
    }
}
