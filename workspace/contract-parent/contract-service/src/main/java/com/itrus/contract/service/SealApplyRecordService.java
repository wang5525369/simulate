package com.itrus.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itrus.contract.entity.SealApplyRecord;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SealApplyRecordService {
    IPage<SealApplyRecord> queryByTime(Date startTime, Date endTime, int pageNum, int pageSize);

    SealApplyRecord queryById(Long id);

    boolean updateSealApplyStatud(Long id,int status);

    List<SealApplyRecord> queryByTime(Date startTime, Date endTime);

}
