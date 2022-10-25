package com.huahuo.model.stamp.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName stamp_detail
 */
@TableName(value ="stamp_detail")
@Data
public class StampDetail implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "stamp_type_id")
    private Integer stampTypeId;

    /**
     * 
     */
    @TableField(value = "life")
    private Integer life;

    /**
     * 
     */
    @TableField(value = "signature")
    private String signature;

    /**
     * 
     */
    @TableField(value = "get_time")
    private LocalDateTime getTime;

    /**
     * 
     */
    @TableField(value = "ownner_id")
    private Integer ownnerId;

    /**
     * 
     */
    @TableField(value = "msg")
    private String msg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StampDetail other = (StampDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStampTypeId() == null ? other.getStampTypeId() == null : this.getStampTypeId().equals(other.getStampTypeId()))
            && (this.getLife() == null ? other.getLife() == null : this.getLife().equals(other.getLife()))
            && (this.getSignature() == null ? other.getSignature() == null : this.getSignature().equals(other.getSignature()))
            && (this.getGetTime() == null ? other.getGetTime() == null : this.getGetTime().equals(other.getGetTime()))
            && (this.getOwnnerId() == null ? other.getOwnnerId() == null : this.getOwnnerId().equals(other.getOwnnerId()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStampTypeId() == null) ? 0 : getStampTypeId().hashCode());
        result = prime * result + ((getLife() == null) ? 0 : getLife().hashCode());
        result = prime * result + ((getSignature() == null) ? 0 : getSignature().hashCode());
        result = prime * result + ((getGetTime() == null) ? 0 : getGetTime().hashCode());
        result = prime * result + ((getOwnnerId() == null) ? 0 : getOwnnerId().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stampTypeId=").append(stampTypeId);
        sb.append(", life=").append(life);
        sb.append(", signature=").append(signature);
        sb.append(", getTime=").append(getTime);
        sb.append(", ownnerId=").append(ownnerId);
        sb.append(", msg=").append(msg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}