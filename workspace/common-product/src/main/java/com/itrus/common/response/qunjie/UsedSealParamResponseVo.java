package com.itrus.common.response.qunjie;

import java.util.Date;

public class UsedSealParamResponseVo {
    String sealId;  //印章ID 申请用印的印章ID
    String id;  //用印申请明细ID 用印申请明细ID
    String sealName;  //印章名称 用印印章明后才能
    String safeCode;  //用印码多人用印时，此处为空在safeCodeInfo中展示授权码
    String sealerUserName; //实际用印人账号多人用印时，此处为空在safeCodeInfo中展示用印人
    int applyCount;  //用印申请次数 用印申请的次数
    int applyCrossPageSealCount;  //骑缝章申请次数 骑缝章申请的次数
    TakeOrPutQrCodeResponseVo takeOrPutQrCode;  //取还章码存放取还章码信息的对象
    int takeOut; //是否外带（0：否，1：是） 是否外带
    Date outStartDate;  //预计外带开始时间
    Date outEndDate;  //预计外带结束时间
    String outAddress;  //外带详细地址
    String province;  //印章外带省份
    String city;  //印章外带城市
    String longitude; //经度 外带地址，经度信息
    String latitude; //纬度 外带地址，纬度信息
    double radius;;  //预留字段 预留字段，暂不使用，响应为空
    String cross;  //预留字段 预留字段，暂不使用，响应为空
    String poi;  //预留字段 预留字段，暂不使用，响应为空
    String road;  //预留字段 预留字段，暂不使用，响应为空
}
