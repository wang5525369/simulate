package com.itrus.common.response.ofd;

import com.itrus.common.pojo.ResponseBasePojo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfdCreateSealResponse extends ResponseBasePojo {
    /**
     * 印章文件ID
     */
    String fileId;
}
