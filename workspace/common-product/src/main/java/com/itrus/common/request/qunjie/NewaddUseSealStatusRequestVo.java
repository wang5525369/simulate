package com.itrus.common.request.qunjie;

import com.itrus.common.pojo.RequestBasePojo;
import lombok.Data;

@Data
public class NewaddUseSealStatusRequestVo extends RequestBasePojo {
    String applyId; //流程申请id
    String sealId;  //印章id
    int applyCount; //要补签的次数
    int applyCrossPageSealCount; //要补签的骑缝章次数

    public NewaddUseSealStatusRequestVo(String applyId,String sealId,int applyCount,int applyCrossPageSealCount){
        this.setApplyId(applyId);
        this.setSealId(sealId);
        this.setApplyCount(applyCount);
        this.setApplyCrossPageSealCount(applyCrossPageSealCount);
    }

}
