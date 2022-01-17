package com.itrus.common.request.qunjie;

import com.itrus.common.pojo.RequestBasePojo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SealAuthorizeRequestVo extends RequestBasePojo {
    String workflowApplyParam;  //申请授 权必填 参数集合
    MultipartFile[] file;  //流程申 请附件
}
