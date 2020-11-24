package com.womenstore.hcf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womenstore.hcf.dao.orders.OrdersMapper;
import com.womenstore.hcf.entity.orders.Orders;
import com.womenstore.hcf.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrderService {
}
