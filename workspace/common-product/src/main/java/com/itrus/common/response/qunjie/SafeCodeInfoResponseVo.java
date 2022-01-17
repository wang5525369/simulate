package com.itrus.common.response.qunjie;

import lombok.Data;

@Data
public class SafeCodeInfoResponseVo {
    String safeCode; //验证码 多人用印，验证码
    String userName;  //用印人 用于登录群杰印章管理平台（仅限英文、数字和.字符）
    String userId;  //用印人ID
}
