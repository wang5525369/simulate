package com.itrus.contract.response.cert;

import com.itrus.contract.pojo.ResponseBasePojo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * <p>
 *  api查询证书响应数据
 * </p>
 *
 * @author hzq
 * @since 2021/9/1
 */
@Getter
@Setter
public class QueryCertResponseVO extends ResponseBasePojo {

    /**
     * 证书数据集
     */
    private List<QueryCertDTO> certData;




}
