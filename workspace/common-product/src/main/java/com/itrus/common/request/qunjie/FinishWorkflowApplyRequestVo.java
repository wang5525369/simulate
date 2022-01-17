package com.itrus.common.request.qunjie;

import com.itrus.common.pojo.RequestBasePojo;
import lombok.Data;

import java.util.List;

@Data
public class FinishWorkflowApplyRequestVo extends RequestBasePojo {
    String applyId; //申请流程id
    List<String> sealIdList; //印章id集合

    public FinishWorkflowApplyRequestVo(String applyId,List<String> sealIdList){
        this.setApplyId(applyId);
        this.setSealIdList(sealIdList);
    }

}
