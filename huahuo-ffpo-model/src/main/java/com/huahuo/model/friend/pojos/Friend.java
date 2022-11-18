package com.huahuo.model.friend.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName friend
 */
@TableName(value ="friend")
@Data
public class Friend implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "friend_id")
    private Integer friendId;

    /**
     * 
     */
    @TableField(value = "friend_type")
    private Integer friendType;

    /**
     * 
     */
    @TableField(value = "mail_num")
    private Integer mailNum;

    /**
     * 
     */
    @TableField(value = "friend_name")
    private String friendName;

    /**
     * 
     */
    @TableField(value = "friend_head_img")
    private String friendHeadImg;

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
        Friend other = (Friend) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFriendId() == null ? other.getFriendId() == null : this.getFriendId().equals(other.getFriendId()))
            && (this.getFriendType() == null ? other.getFriendType() == null : this.getFriendType().equals(other.getFriendType()))
            && (this.getMailNum() == null ? other.getMailNum() == null : this.getMailNum().equals(other.getMailNum()))
            && (this.getFriendName() == null ? other.getFriendName() == null : this.getFriendName().equals(other.getFriendName()))
            && (this.getFriendHeadImg() == null ? other.getFriendHeadImg() == null : this.getFriendHeadImg().equals(other.getFriendHeadImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFriendId() == null) ? 0 : getFriendId().hashCode());
        result = prime * result + ((getFriendType() == null) ? 0 : getFriendType().hashCode());
        result = prime * result + ((getMailNum() == null) ? 0 : getMailNum().hashCode());
        result = prime * result + ((getFriendName() == null) ? 0 : getFriendName().hashCode());
        result = prime * result + ((getFriendHeadImg() == null) ? 0 : getFriendHeadImg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", friendId=").append(friendId);
        sb.append(", friendType=").append(friendType);
        sb.append(", mailNum=").append(mailNum);
        sb.append(", friendName=").append(friendName);
        sb.append(", friendHeadImg=").append(friendHeadImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}