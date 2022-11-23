package com.huahuo.model.mail.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName shipping_mail
 */
@TableName(value ="shipping_mail")
@Data
public class ShippingMail implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "is_send")
    private Integer isSend;

    /**
     * 
     */
    @TableField(value = "send_time")
    private String sendTime;

    /**
     * 
     */
    @TableField(value = "get_id")
    private Integer getId;

    /**
     * 
     */
    @TableField(value = "send_id")
    private Integer sendId;

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
        ShippingMail other = (ShippingMail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsSend() == null ? other.getIsSend() == null : this.getIsSend().equals(other.getIsSend()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getGetId() == null ? other.getGetId() == null : this.getGetId().equals(other.getGetId()))
            && (this.getSendId() == null ? other.getSendId() == null : this.getSendId().equals(other.getSendId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsSend() == null) ? 0 : getIsSend().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getGetId() == null) ? 0 : getGetId().hashCode());
        result = prime * result + ((getSendId() == null) ? 0 : getSendId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", isSend=").append(isSend);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", getId=").append(getId);
        sb.append(", sendId=").append(sendId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}