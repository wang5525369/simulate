package com.itrus.contract.request.seal;

import com.itrus.contract.pojo.RequestBasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ApiModel(value = "用印申请记录查询")
public class SealApplyRecordRequestVo extends RequestBasePojo {

    @ApiModelProperty(value = "用印申请记录ID")
    Long id;

    @ApiModelProperty(value = "用印申请编号")
    String approveNo;

    @ApiModelProperty(value = "用印申请标题")
    String applyTitle;

    @ApiModelProperty(value = "用印申请状态")
    Integer status;

    @ApiModelProperty(value = "查询开始时间")
    Date startTime;

    @ApiModelProperty(value = "查询结束时间")
    Date endTime;

    @ApiModelProperty(value = "页码")
    private Integer pageNumber;

    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;
}
