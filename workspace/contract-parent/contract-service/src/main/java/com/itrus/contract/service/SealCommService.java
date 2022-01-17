package com.itrus.contract.service;

import com.itrus.contract.entity.Seal;

public interface SealCommService {
    /**
     * 根据印章ID查询印章
     * @param sealId
     */
    Seal selectById(Long sealId);

    /**
     * 保存印章信息
     * @param seal
     * @return
     */
    Seal saveSeal(Seal seal);

    /**
     * 修改印章信息根据企业ID
     * @param seal
     * @return
     */
    int updateByEnterpriseId(Seal seal,String enterpriseId);
}
