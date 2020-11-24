package com.womenstore.hcf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womenstore.hcf.dao.cart.CartMapper;
import com.womenstore.hcf.dao.category.CategoryMapper;
import com.womenstore.hcf.entity.cart.Cart;
import com.womenstore.hcf.service.ICartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
}
