package com.liteims.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liteims.entity.Product;
import com.liteims.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StockTask {
    @Autowired
    private ProductMapper productMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void checkStock() {
        log.info("开始执行库存检查任务...");

        // 查询库存 < 10 的商品
        List<Product> lowStockProducts = productMapper.selectList(
                new QueryWrapper<Product>().lt("stock", 10)
        );

        if (lowStockProducts.isEmpty()) {
            log.info("库存充足，无预警信息。");
        } else {
            for (Product p : lowStockProducts) {
                log.warn("【库存预警】商品 ID: {}, 名称: {}, 当前库存: {}",
                        p.getId(), p.getName(), p.getStock());
            }
        }
    }
}