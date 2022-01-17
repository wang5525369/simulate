package com.itrus.contract.tools.enums.file;

import com.itrus.contract.tools.enums.base.BaseEnum;

public enum  LastFileTypeEnum implements BaseEnum {

    PDF(1,"pdf"),OFD(2,"ofd");

    private LastFileTypeEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
