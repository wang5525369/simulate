package com.itrus.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itrus.contract.entity.Seal;
import com.itrus.contract.mapper.SealMapper;
import com.itrus.contract.service.SealCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SealCommServiceImpl implements SealCommService {

    @Autowired
    SealMapper sealMapper;

    @Override
    public Seal selectById(Long sealId) {
        QueryWrapper<Seal> queryWrapper = new QueryWrapper<Seal>();
        queryWrapper.lambda().eq(Seal::getId,sealId).eq(Seal::getDeleted,0);
        return sealMapper.selectOne(queryWrapper);
    }

    public Seal saveSeal(Seal seal){
        sealMapper.insert(seal);
        return seal;
    }

    public int updateByEnterpriseId(Seal seal,String enterpriseId){
        QueryWrapper<Seal> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Seal::getEnterpriseId,enterpriseId);
        return sealMapper.update(seal, queryWrapper);
    }
}
