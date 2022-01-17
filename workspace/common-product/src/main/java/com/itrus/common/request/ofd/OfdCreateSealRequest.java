package com.itrus.common.request.ofd;

import com.itrus.common.pojo.RequestBasePojo;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class OfdCreateSealRequest extends RequestBasePojo {
    /**
     * 印章图片Base64
     */
    @NotNull
    String sealData;

    /**
     * 图片类型 GIF, BMP, JPG, PNG, SVG, OFD
     * 0,1,2,3,4,5 默认为3即PNG
     */
    Integer picType = 3;

    /**
     * 支持的证书ID列表，不能为空
     */
    @NotNull
    Long [] certList;

    /**
     * 印章名称，默认为专用章
     */
    String name;

    /**
     * 业务系统印章ID，若不传以UUID代替
     */
    String sealId;

    /**
     * 过期时间，默认值为1，需要大于0
     */
    Integer expire = 1;

    /**
     * 时间单位，0 年 1 月 2 日，默认为 0
     */
    Integer timeUnit = 0;

    /**
     * 印章类型，默认为1，业务系统自定
     */
     Integer sealType = 1;
}
