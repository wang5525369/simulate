package com.itrus.common.result.uag.response.uag;

import com.itrus.uc.common.bean.open.resp.user.list.UserListByUuidResp;
import com.itrus.uc.common.bean.portal.resp.user.BaseUserInfoResp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UagUserListByUuidResp extends UserListByUuidResp {
    public UagUserListByUuidResp(List<BaseUserInfoResp> userList) {
        super(userList);
    }
}
