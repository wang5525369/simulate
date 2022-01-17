package com.itrus.common.response.qunjie;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class SealAuthorizeResponseVo {
    String safeCode; //验证码 解锁印控台的验证码
    List<SafeCodeInfoResponseVo> safeCodeInfo; //多人用印验证码
    String applyId;  //流程申请ID 用印申请成功时返回的当前用印操作的唯一标识
    String workflowBaseId; //流程实例ID新建流程时的ID，用来做唯一标识，申请用印流程时要采用哪个流程实例就返回哪个流程实例的ID
    String formId; //流程的表单ID 流程所使用的表单格式
    String title; //用印申请流程的名称
    String applyUserId; //申请人的ID 用户ID
    String applyUserName; //流程申请人 如：管理员
    int status; //用印流程的状态 0：未开始，1：审核处理中，2：结束，3：已授权
    String stepName; //步骤名称 预留字段，暂未使用，响应为空
    String lastStepId; //最后流程步编码
    String creator; //创建人的ID
    Date createDate; //创建时间 如：2020-06-01 14:10:33
    String updater;  //更新人 预留字段，暂未使用
    Date updateDate; //更新时间 预留字段，暂未使用
    String companyId;  //公司ID
    String tableInfo; //申请表摘要信息 预留字段，暂未使用
    String departOrgNo; //组织代码
    String applyerUserName; //  预留字段 预留字段，暂未使用，响应为空
    String applyNo;  //流程单号 唯一值
    String applyNoTitle; //流程单号和用印申请流程的名称结合格式：[流程单号]流程申请名称如：[YY-20200606]测试流程3311
    Integer tableType;  //表名 预留字段，暂未使用
    List<String> fileList;  //文件信息 预留字段，暂未使用
    List<UsedSealParamResponseVo> usedSealParamList; //印章使用明细信息
    String departmentId; //所属部门或子公司的id 流程申请人所属默认部门id
    String departOrgType; //组织类型（1.部门2.公司）
    OptionResponseVo option;  //开关配置项 具体参数如下表：开关配置项
}
