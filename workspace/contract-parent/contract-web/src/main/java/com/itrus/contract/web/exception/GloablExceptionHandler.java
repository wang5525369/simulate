package com.itrus.contract.web.exception;

import com.itrus.common.exception.MiniException;
import com.itrus.contract.tools.domain.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MiniException.class)
    public HttpResponse handleException(MiniException miniException) {
        String msg = miniException.getMessage();
        if (StringUtils.isBlank(msg)) {
            msg = "服务器出错";
        }
        int code = miniException.getCode();
        return HttpResponse.of().code(code, msg);
    }
}
