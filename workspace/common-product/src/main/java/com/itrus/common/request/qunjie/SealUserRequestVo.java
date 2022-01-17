package com.itrus.common.request.qunjie;

import com.itrus.common.pojo.RequestBasePojo;
import lombok.Data;

@Data
public class SealUserRequestVo extends RequestBasePojo {
    String sealId;  //印章ID
    String userIds; //用户ID,多个用,分隔

    public SealUserRequestVo(String sealId, String userIds){
        this.setSealId(sealId);
        this.setUserIds(userIds);
    }

}
