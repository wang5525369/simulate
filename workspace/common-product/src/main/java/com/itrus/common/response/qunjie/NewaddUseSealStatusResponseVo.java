package com.itrus.common.response.qunjie;

import lombok.Data;

@Data
public class NewaddUseSealStatusResponseVo {
    String sealId;  //印章id
    String sealName; //印章名称
    String applyCount;  //申请数量 补签申请的数量
    String applyCrossPageSealCount; //  骑缝章的申请数量 补签申请的骑缝章数量
    String safeCode;  //授权码 补签刷新后的授权码
    String sealerUserName;  //用印申请人
    String takeOut;  //是否外带 （0：否，1：是）
    String outAddress;  //外带详细地址
    String outStartDate;  //外带开始时间
    String outEndDate; //外带结束时间
}
