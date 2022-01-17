package com.itrus.contract.request.cert;

import com.itrus.common.pojo.RequestBasePojo;
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
public class CertQueryApiRequestVO extends RequestBasePojo {

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
