package com.itrus.common.request.qunjie;

import lombok.Data;

import java.util.List;

@Data
public class WorkflowApplyParamRequestVo {
    String title; //流程申请单标题
    String callBackUrl; //回调url路径
    String applyerUserName; //申请人账号 Y 流程申请的人账号，如：admin
    List<UsedSealParamRequestVo> usedSealParamList; //申请明细信息 Y
    OptionRequestVo option; //开关配置项对象
    /*
    //明文水印内容
    //方式一：billNo和printBillNo都传值，我们这边流程号和水印都用传过来的数据;
    //方式二：billNo传值，printBillNo不传值，printBillNo使用billNo的数据保存;
    //方式三：billNo不传值,printBillNo传值，billNo使用群杰默认的生成方式保存，printBillNo使用传 递的值；
    //方式四：billNo和printBillNo都不传值，billNo使用群杰默认的生成方式保存，printBillNo使 用 billNo的值；
     */
    String printBillNo;
    String billNo; //第三方流程编号
}
