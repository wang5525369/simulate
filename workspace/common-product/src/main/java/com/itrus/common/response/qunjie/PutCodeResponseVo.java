package com.itrus.common.response.qunjie;

import lombok.Data;

@Data
public class PutCodeResponseVo {
    String msg; //提示消息 该章未在印章柜汇中初始化或者查询成功
    String vrCode;  //取还章码 存放取章或者还章码信息
}
