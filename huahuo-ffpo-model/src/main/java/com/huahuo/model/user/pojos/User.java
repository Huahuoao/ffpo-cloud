package com.huahuo.model.user.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 性别

     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 特征一
     */
    @TableField(value = "first_character")
    private Integer firstCharacter;

    /**
     * 特征二
     */
    @TableField(value = "second_character")
    private Integer secondCharacter;

    /**
     * 特征三
     */
    @TableField(value = "third_character")
    private Integer thirdCharacter;

    /**
     * 特征四
     */
    @TableField(value = "forth_character")
    private Integer forthCharacter;

    /**
     * 特征五
     */
    @TableField(value = "constellation")
    private Integer constellation;

    /**
     * 用户等级
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 邮票数量
     */
    @TableField(value = "stamp_num")
    private Integer stampNum;

    /**
     * 心理年龄
     */
    @TableField(value = "psychological_age")
    private Integer psychologicalAge;

    /**
     * 发件数量
     */
    @TableField(value = "send_msg_num")
    private Integer sendMsgNum;

    /**
     * 收件数量

     */
    @TableField(value = "get_msg_num")
    private Integer getMsgNum;

    /**
     * 头像图片url
     */
    @TableField(value = "head_img")
    private String headImg;

    /**
     * 个性签名
     */
    @TableField(value = "signature")
    private String signature;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 金币数量
     */
    @TableField(value = "coin_num")
    private Integer coinNum;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 创建时间
     */
    @TableField(value = "crete_time")
    private LocalDateTime creteTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 邮票包id
     */
    @TableField(value = "stamp_bag_id")
    private Integer stampBagId;

    /**
     * 
     */
    @TableField(value = "single_img_id")
    private String singleImgId;

    /**
     * 
     */
    @TableField(value = "single_img_id_url")
    private String singleImgIdUrl;

    /**
     * 
     */
    @TableField(value = "salt")
    private String salt;

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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getFirstCharacter() == null ? other.getFirstCharacter() == null : this.getFirstCharacter().equals(other.getFirstCharacter()))
            && (this.getSecondCharacter() == null ? other.getSecondCharacter() == null : this.getSecondCharacter().equals(other.getSecondCharacter()))
            && (this.getThirdCharacter() == null ? other.getThirdCharacter() == null : this.getThirdCharacter().equals(other.getThirdCharacter()))
            && (this.getForthCharacter() == null ? other.getForthCharacter() == null : this.getForthCharacter().equals(other.getForthCharacter()))
            && (this.getConstellation() == null ? other.getConstellation() == null : this.getConstellation().equals(other.getConstellation()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getStampNum() == null ? other.getStampNum() == null : this.getStampNum().equals(other.getStampNum()))
            && (this.getPsychologicalAge() == null ? other.getPsychologicalAge() == null : this.getPsychologicalAge().equals(other.getPsychologicalAge()))
            && (this.getSendMsgNum() == null ? other.getSendMsgNum() == null : this.getSendMsgNum().equals(other.getSendMsgNum()))
            && (this.getGetMsgNum() == null ? other.getGetMsgNum() == null : this.getGetMsgNum().equals(other.getGetMsgNum()))
            && (this.getHeadImg() == null ? other.getHeadImg() == null : this.getHeadImg().equals(other.getHeadImg()))
            && (this.getSignature() == null ? other.getSignature() == null : this.getSignature().equals(other.getSignature()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getCoinNum() == null ? other.getCoinNum() == null : this.getCoinNum().equals(other.getCoinNum()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getCreteTime() == null ? other.getCreteTime() == null : this.getCreteTime().equals(other.getCreteTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getStampBagId() == null ? other.getStampBagId() == null : this.getStampBagId().equals(other.getStampBagId()))
            && (this.getSingleImgId() == null ? other.getSingleImgId() == null : this.getSingleImgId().equals(other.getSingleImgId()))
            && (this.getSingleImgIdUrl() == null ? other.getSingleImgIdUrl() == null : this.getSingleImgIdUrl().equals(other.getSingleImgIdUrl()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getFirstCharacter() == null) ? 0 : getFirstCharacter().hashCode());
        result = prime * result + ((getSecondCharacter() == null) ? 0 : getSecondCharacter().hashCode());
        result = prime * result + ((getThirdCharacter() == null) ? 0 : getThirdCharacter().hashCode());
        result = prime * result + ((getForthCharacter() == null) ? 0 : getForthCharacter().hashCode());
        result = prime * result + ((getConstellation() == null) ? 0 : getConstellation().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getStampNum() == null) ? 0 : getStampNum().hashCode());
        result = prime * result + ((getPsychologicalAge() == null) ? 0 : getPsychologicalAge().hashCode());
        result = prime * result + ((getSendMsgNum() == null) ? 0 : getSendMsgNum().hashCode());
        result = prime * result + ((getGetMsgNum() == null) ? 0 : getGetMsgNum().hashCode());
        result = prime * result + ((getHeadImg() == null) ? 0 : getHeadImg().hashCode());
        result = prime * result + ((getSignature() == null) ? 0 : getSignature().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getCoinNum() == null) ? 0 : getCoinNum().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getCreteTime() == null) ? 0 : getCreteTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getStampBagId() == null) ? 0 : getStampBagId().hashCode());
        result = prime * result + ((getSingleImgId() == null) ? 0 : getSingleImgId().hashCode());
        result = prime * result + ((getSingleImgIdUrl() == null) ? 0 : getSingleImgIdUrl().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", sex=").append(sex);
        sb.append(", firstCharacter=").append(firstCharacter);
        sb.append(", secondCharacter=").append(secondCharacter);
        sb.append(", thirdCharacter=").append(thirdCharacter);
        sb.append(", forthCharacter=").append(forthCharacter);
        sb.append(", constellation=").append(constellation);
        sb.append(", level=").append(level);
        sb.append(", stampNum=").append(stampNum);
        sb.append(", psychologicalAge=").append(psychologicalAge);
        sb.append(", sendMsgNum=").append(sendMsgNum);
        sb.append(", getMsgNum=").append(getMsgNum);
        sb.append(", headImg=").append(headImg);
        sb.append(", signature=").append(signature);
        sb.append(", address=").append(address);
        sb.append(", nickName=").append(nickName);
        sb.append(", coinNum=").append(coinNum);
        sb.append(", age=").append(age);
        sb.append(", creteTime=").append(creteTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", stampBagId=").append(stampBagId);
        sb.append(", singleImgId=").append(singleImgId);
        sb.append(", singleImgIdUrl=").append(singleImgIdUrl);
        sb.append(", salt=").append(salt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}