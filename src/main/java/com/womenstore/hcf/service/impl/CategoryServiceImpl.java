package com.womenstore.hcf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womenstore.hcf.dao.category.CategoryMapper;
import com.womenstore.hcf.entity.category.Category;
import com.womenstore.hcf.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
}
