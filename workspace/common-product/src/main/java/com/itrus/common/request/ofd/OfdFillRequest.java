package com.itrus.common.request.ofd;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class OfdFillRequest {
    /**
     * 二选一	OFD文档地址，如果 ofdURI和ofdData都传，则取ofdURI
     */
    String ofdURI;
    /**
     * 二选一	OFD文档的Base64字符串，如果 ofdURI和ofdData都传，则取ofdURI
     */
    String ofdData;
    /**
     * 可选	返回类型： base64 ：返回文档base64编码（默认） fss : 返回文档存储后的ID
     */
    String returnType;
    /**
     * 组	必选	填充的参数组
     */
    List<OfdFillTemplateRequest> templates;
}
