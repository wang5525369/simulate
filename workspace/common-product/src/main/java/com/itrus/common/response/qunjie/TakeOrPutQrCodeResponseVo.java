package com.itrus.common.response.qunjie;

import lombok.Data;

@Data
public class TakeOrPutQrCodeResponseVo {
    TakeCodeResponseVo takeCode;
    PutCodeResponseVo putCode;
}
