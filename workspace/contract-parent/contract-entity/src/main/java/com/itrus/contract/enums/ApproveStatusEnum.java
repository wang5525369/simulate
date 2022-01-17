package com.itrus.contract.enums;

public enum ApproveStatusEnum {
    Appl(1,"审批中"),
    Agree(2,"已同意"),
    Reject(3,"已拒绝");

    private String name;
    private int code;

    private ApproveStatusEnum(int code,String name) {
        this.code = code;
        this.name = name;
    }

    static String getName(int code){
        for (ApproveStatusEnum approveStatusEnum : ApproveStatusEnum.values()) {
            if (approveStatusEnum.code == code) {
                return approveStatusEnum.name;
            }
        }
        return null;
    }
}
