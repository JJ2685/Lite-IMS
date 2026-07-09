package com.liteims.service.impl;

import com.liteims.entity.Customer;
import com.liteims.mapper.CustomerMapper;
import com.liteims.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
}
