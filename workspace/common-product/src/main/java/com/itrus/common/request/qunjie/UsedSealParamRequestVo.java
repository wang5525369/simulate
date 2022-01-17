package com.itrus.common.request.qunjie;

import lombok.Data;

import java.util.Date;

@Data
public class UsedSealParamRequestVo {
    String sealId; //印章ID
    String applyCount; //用印数量
    String applyCrossPageSealCount; //骑缝章次数
    String sealerUserName;  //实际用印人账号 实际用印人的真实姓名，此处用印人不填写参数，默认使用流程申请人做为用印人
    String takeOut; //是否外带 （0：否，1：是）
    Date outStartDate; //预计外带开始时间如果要填值，格式如："yyyy-MM-dd HH:mm:ss"
    Date outEndDate; //预计外带结束时间如果要填值，格式如："yyyy-MM-dd HH:mm:ss"
    String outAddress; //外带详细地址
    String city; //外带城市预留字段预留字段，暂不使用，响应为空
    String longitude; //外带地点的经度 参数必须是高德地图api提供的经纬度
    String latitude; //外带地点的维度
}
