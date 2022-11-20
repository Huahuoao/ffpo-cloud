package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName mail
 */
@TableName(value ="mail")
@Data
public class Mail implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 是否送出
     */
    @TableField(value = "is_send")
    private Integer isSend;

    /**
     * 送出的用户的id
     */
    @TableField(value = "send_user_id")
    private Integer sendUserId;

    /**
     * 收到的用户的id
     */
    @TableField(value = "get_user_id")
    private Integer getUserId;

    /**
     * 使用的邮票id
     */
    @TableField(value = "stamp_id")
    private Integer stampId;

    /**
     * 文本
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 创建时间
     */
    @TableField(value = "crete_time")
    private LocalDateTime creteTime;

    /**
     * 送达时间
     */
    @TableField(value = "send_time")
    private LocalDateTime sendTime;

    /**
     * 0是收件箱 1是发件箱 2是草稿
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 1是删除0是不删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 
     */
    @TableField(value = "stamp_img")
    private String stampImg;

    /**
     * 
     */
    @TableField(value = "send_add")
    private String sendAdd;

    /**
     * 
     */
    @TableField(value = "get_add")
    private String getAdd;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "is_like")
    private Integer isLike;

    /**
     * 
     */
    @TableField(value = "title")
    private String title;

    /**
     * 
     */
    @TableField(value = "tags")
    private String tags;

    /**
     * 
     */
    @TableField(value = "is_public")
    private Integer isPublic;

    /**
     * 
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 
     */
    @TableField(value = "collect_num")
    private Integer collectNum;

    /**
     * 
     */
    @TableField(value = "comment_num")
    private Integer commentNum;

    /**
     * 
     */
    @TableField(value = "send_user_name")
    private String sendUserName;

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
        Mail other = (Mail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsSend() == null ? other.getIsSend() == null : this.getIsSend().equals(other.getIsSend()))
            && (this.getSendUserId() == null ? other.getSendUserId() == null : this.getSendUserId().equals(other.getSendUserId()))
            && (this.getGetUserId() == null ? other.getGetUserId() == null : this.getGetUserId().equals(other.getGetUserId()))
            && (this.getStampId() == null ? other.getStampId() == null : this.getStampId().equals(other.getStampId()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
            && (this.getCreteTime() == null ? other.getCreteTime() == null : this.getCreteTime().equals(other.getCreteTime()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getStampImg() == null ? other.getStampImg() == null : this.getStampImg().equals(other.getStampImg()))
            && (this.getSendAdd() == null ? other.getSendAdd() == null : this.getSendAdd().equals(other.getSendAdd()))
            && (this.getGetAdd() == null ? other.getGetAdd() == null : this.getGetAdd().equals(other.getGetAdd()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIsLike() == null ? other.getIsLike() == null : this.getIsLike().equals(other.getIsLike()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getIsPublic() == null ? other.getIsPublic() == null : this.getIsPublic().equals(other.getIsPublic()))
            && (this.getLikeNum() == null ? other.getLikeNum() == null : this.getLikeNum().equals(other.getLikeNum()))
            && (this.getCollectNum() == null ? other.getCollectNum() == null : this.getCollectNum().equals(other.getCollectNum()))
            && (this.getCommentNum() == null ? other.getCommentNum() == null : this.getCommentNum().equals(other.getCommentNum()))
            && (this.getSendUserName() == null ? other.getSendUserName() == null : this.getSendUserName().equals(other.getSendUserName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsSend() == null) ? 0 : getIsSend().hashCode());
        result = prime * result + ((getSendUserId() == null) ? 0 : getSendUserId().hashCode());
        result = prime * result + ((getGetUserId() == null) ? 0 : getGetUserId().hashCode());
        result = prime * result + ((getStampId() == null) ? 0 : getStampId().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getCreteTime() == null) ? 0 : getCreteTime().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getStampImg() == null) ? 0 : getStampImg().hashCode());
        result = prime * result + ((getSendAdd() == null) ? 0 : getSendAdd().hashCode());
        result = prime * result + ((getGetAdd() == null) ? 0 : getGetAdd().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIsLike() == null) ? 0 : getIsLike().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getIsPublic() == null) ? 0 : getIsPublic().hashCode());
        result = prime * result + ((getLikeNum() == null) ? 0 : getLikeNum().hashCode());
        result = prime * result + ((getCollectNum() == null) ? 0 : getCollectNum().hashCode());
        result = prime * result + ((getCommentNum() == null) ? 0 : getCommentNum().hashCode());
        result = prime * result + ((getSendUserName() == null) ? 0 : getSendUserName().hashCode());
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
        sb.append(", sendUserId=").append(sendUserId);
        sb.append(", getUserId=").append(getUserId);
        sb.append(", stampId=").append(stampId);
        sb.append(", msg=").append(msg);
        sb.append(", creteTime=").append(creteTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", type=").append(type);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", stampImg=").append(stampImg);
        sb.append(", sendAdd=").append(sendAdd);
        sb.append(", getAdd=").append(getAdd);
        sb.append(", userId=").append(userId);
        sb.append(", isLike=").append(isLike);
        sb.append(", title=").append(title);
        sb.append(", tags=").append(tags);
        sb.append(", isPublic=").append(isPublic);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", collectNum=").append(collectNum);
        sb.append(", commentNum=").append(commentNum);
        sb.append(", sendUserName=").append(sendUserName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}