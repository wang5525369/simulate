package com.itrus.contract.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itrus.contract.entity.ContractDocument;
import com.itrus.contract.entity.PhysicalSeal;
import com.itrus.contract.entity.SealApplyRecord;
import com.itrus.contract.request.seal.SealApplyRecordRequestVo;
import com.itrus.contract.web.service.ISealApplyRecordWebService;
import com.itrus.contract.tools.domain.HttpResponse;
import com.itrus.contract.web.service.IContractDocumentWebService;
import com.itrus.contract.web.service.IPhysicalSealWebService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 物理印章表 前端控制器
 * </p>
 *
 * @author hzq
 * @since 2021-09-30
 */
@RestController
@RequestMapping("/contractweb/physicalSealApplyRecord")
public class PhysicalSealApplyRecordWebController {

    @Autowired
    ISealApplyRecordWebService sealApplyRecordService;

    @Autowired
    IContractDocumentWebService contractDocumentWebService;

    @Autowired
    IPhysicalSealWebService physicalSealWebService;

    /**用印审批功能
    /**
     * 用印审批功能
     *
     * @param sealApplyRecordRequestVo
     * @return
     */
    @PostMapping("/approvalUseSeal")
    public HttpResponse approvalUseSeal(@Validated @RequestBody SealApplyRecordRequestVo sealApplyRecordRequestVo) {
        Long id = sealApplyRecordRequestVo.getId();
        Integer status = sealApplyRecordRequestVo.getStatus();
        if (ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(status)) {
            return HttpResponse.of().error("参数非法");
        }
        boolean bRet = sealApplyRecordService.updateSealApplyStatud(id, status);
        if (bRet == false) {
            return HttpResponse.of().error("非法操作");
        } else {
            return HttpResponse.of();
        }
    }

    /**
     * 用印申请记录列表查询
     *
     * @param sealApplyRecordRequestVo
     * @return
     */
    @PostMapping("/getApplyUseSealListInfo")
    public HttpResponse getApplyUseSealListInfo(@Validated @RequestBody SealApplyRecordRequestVo sealApplyRecordRequestVo) {
        Date startTime = sealApplyRecordRequestVo.getStartTime();
        Date endTime = sealApplyRecordRequestVo.getEndTime();
        int pageNum = sealApplyRecordRequestVo.getPageNumber();
        int pageSize = sealApplyRecordRequestVo.getPageSize();
        IPage<SealApplyRecord> pageInfo = sealApplyRecordService.queryByTime(startTime, endTime, pageNum, pageSize);
        return HttpResponse.of().data(pageInfo);
    }

    /**
     * 用印申请记录导出功能
     *
     * @param sealApplyRecordRequestVo
     * @return
     */
    @PostMapping("/exportApplyUseSealInfo")
    public HttpResponse exportApplyUseSealInfo(@Validated @RequestBody SealApplyRecordRequestVo sealApplyRecordRequestVo) {
        Date startTime = sealApplyRecordRequestVo.getStartTime();
        Date endTime = sealApplyRecordRequestVo.getEndTime();
        List<SealApplyRecord> sealApplyRecordList = sealApplyRecordService.queryByTime(startTime, endTime);
        return HttpResponse.of().data(sealApplyRecordList);
    }

    /**
     * 用印申请详情查看功能
     *
     * @param sealApplyRecordRequestVo
     * @return
     */
    @PostMapping("/getApplyUseSealInfo")
    public HttpResponse getApplyUseSealInfo(@Validated @RequestBody SealApplyRecordRequestVo sealApplyRecordRequestVo) {
        Long id = sealApplyRecordRequestVo.getId();
        JSONObject jsonObject = new JSONObject();
        String message = "";
        SealApplyRecord sealApplyRecord = sealApplyRecordService.getById(id);
        if (ObjectUtils.isEmpty(sealApplyRecord)){
            message = "印章申请ID不存在";
            return HttpResponse.of().error(message);
        }

        Long sealId = sealApplyRecord.getSealId();
        Long contractId = sealApplyRecord.getContractId();
        PhysicalSeal physicalSeal = physicalSealWebService.getById(contractId);
        if (ObjectUtils.isEmpty(physicalSeal)){
            message = "印章ID不存在";
            return HttpResponse.of().error(message);
        }

        List<ContractDocument> contractDocumentList = contractDocumentWebService.listByContractId(contractId);
        if (CollectionUtils.isEmpty(contractDocumentList)){
            message = "合同文档不存在";
            return HttpResponse.of().error(message);
        }
        jsonObject = (JSONObject) JSONObject.toJSON(sealApplyRecord);
        jsonObject.put("sealFileId",physicalSeal.getFileId());
        jsonObject.put("documentList",contractDocumentList);

        return HttpResponse.of().data(jsonObject);
    }

}