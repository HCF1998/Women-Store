package com.womenstore.hcf.entity.user;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Accessors(chain = true)
@TableName(value = "user")
@Builder
public class User {

    /**
     * 用户id
     */
    @TableId(value = "user_Id",type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户账号
     */
    @TableField(value = "user_Acount")
    private String userAcount;

    /**
     * 用户名
     */
    @TableField(value = "user_Name")
    private String userName;

    /**
     * 用户电话
     */
    @TableField(value = "user_Phone")
    private String userPhone;

    /**
     * 用户权限，0-普通用户，1-管理员
     */
    @TableField(value = "user_Priority")
    private Integer userPriority;

    /**
     * 用户收货地址
     */
    @TableField(value = "user_Address")
    private String userAddress;

    /**
     * 用户密码
     */
    @TableField(value = "user_Password")
    private String userPassword;

    /**
     * 用户状态，0-封禁，1-正常
     */
    @TableField(value = "user_Status")
    private Integer userStatus;
}
