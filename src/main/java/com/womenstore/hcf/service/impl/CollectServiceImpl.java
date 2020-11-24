package com.womenstore.hcf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womenstore.hcf.dao.collect.CollectMapper;
import com.womenstore.hcf.entity.collect.Collect;
import com.womenstore.hcf.service.ICollectService;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {
}
