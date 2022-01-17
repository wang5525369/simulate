package com.itrus.common.uag;

import com.itrus.uc.api.CompanyApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "uagCompanyApiRequest", value = "${http.uag}",path = "/orgApi")
public interface UagCompanyApiRequest extends CompanyApi {
}
