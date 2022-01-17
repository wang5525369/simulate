package com.wangxb.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2021-10-26
 */
@TableName("flow_info")
public class FlowInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String flowId;

    private String flowKey;

    private String flowName;

    private Boolean isDelete;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;

    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }
    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "FlowInfo{" +
            "id=" + id +
            ", flowId=" + flowId +
            ", flowKey=" + flowKey +
            ", flowName=" + flowName +
            ", isDelete=" + isDelete +
            ", createTime=" + createTime +
            ", createUser=" + createUser +
            ", updateTime=" + updateTime +
            ", updateUser=" + updateUser +
            ", version=" + version +
        "}";
    }
}
