package com.itrus.common.request.ofd;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class OfdBatchSignRequest {
    /**
     * 二选 一;OFD文档地址，参考附录[附录9.2](# 9.2 URI资源说明)，如果ofdURI和ofdData都传，则取ofdURI
     */
    String ofdURI;

    /**
     * 二选 一;OFD文档的Base64字符串，如果ofdURI和ofdData都传，则取ofdURI
     */
    String ofdData;

    /**
     * 返回类型：base64 ：返回文档base64编码（默认）,fss : 返回文档存储后的ID
     */
    String returnType = "base64";

    OfdBatchSignParamRequest [] params;
}
