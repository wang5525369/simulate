package com.itrus.common.http;

import com.itrus.common.request.atom.CreateCircularSealRequest;
import com.itrus.common.request.dsvs.NewSignRequestVO;
import com.itrus.common.request.ofd.OfdCreateSealRequest;
import com.itrus.common.response.dsvs.NewSignResponseVO;
import com.itrus.common.response.ofd.OfdCreateSealResponse;
import com.itrus.common.response.ra.LogIgnoreRaResult;
import com.itrus.common.response.ra.RaResult;
import com.itrus.common.result.uag.UagResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(contextId ="ofdApiRequest", value = "${http.ofd}") //value:被调用的服务在注册中心的名称
public interface OfdApiRequest {
    /**
     * 创建OFD印章
     *
     * @param ofdCreateSealRequest 请求参数
     * @return 返回创建结果
     * @throws Exception 抛出异常
     */
    @RequestMapping(value = "/ofd/createEsl", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UagResult<OfdCreateSealResponse> createEsl(OfdCreateSealRequest ofdCreateSealRequest);

    /**
     * 创建OFD印章
     *
     * @param ofdCreateSealRequest 请求参数
     * @return 返回创建结果
     * @throws Exception 抛出异常
     */
    @RequestMapping(value = "/ofd/sign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    RaResult<NewSignResponseVO> sign(OfdSignRequestVO obj);
}

