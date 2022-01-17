package com.itrus.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itrus.contract.entity.PhysicalSeal;
import com.itrus.contract.entity.SealApplyRecord;
import com.itrus.contract.mapper.PhysicalSealMapper;
import com.itrus.contract.service.PhysicalSealService;
import org.springframework.beans.factory.annotation.Autowired;

public class PhysicalSealServiceImpl implements PhysicalSealService {

    @Autowired
    PhysicalSealMapper physicalSealMapper;

    @Override
    public PhysicalSeal queryById(Long id) {
        QueryWrapper<PhysicalSeal> queryWrapper = new QueryWrapper<PhysicalSeal>();
        queryWrapper.eq("id",id);
        PhysicalSeal physicalSeal = physicalSealMapper.selectOne(queryWrapper);
        return physicalSeal;
    }
}
