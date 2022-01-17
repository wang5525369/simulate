package com.itrus.common.uag;


import com.itrus.uc.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "uagUserApiRequest", value = "${http.uag}",path = "/userApi")
public interface UagUserApiRequest extends UserApi {
}
