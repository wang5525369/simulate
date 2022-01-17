package com.itrus.contract.demo.cert;

import lombok.Data;

/**
 * <p>
 *  api证书查询请求参数
 * </p>
 *
 * @author hzq
 * @since 2021/9/1
 */
@Data
public class CertQueryApiRequestVO {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 证书类型
     */
    private Integer certType;

}
