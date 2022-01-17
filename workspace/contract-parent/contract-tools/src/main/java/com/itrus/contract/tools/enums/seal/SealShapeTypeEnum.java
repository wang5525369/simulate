package com.itrus.contract.tools.enums.seal;

import com.itrus.contract.tools.enums.base.BaseEnum;
import lombok.Getter;

@Getter
public enum SealShapeTypeEnum implements BaseEnum {
    SEAL_SHAPEE_CIRCULAR(1,"圆章"),
    SEAL_SHAPEE_ELLIPSE(2,"椭圆章"),
    SEAL_SHAPEE_TRUANGLE(3,"三角章");

    private Integer code;
    private String name;

    private SealShapeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
