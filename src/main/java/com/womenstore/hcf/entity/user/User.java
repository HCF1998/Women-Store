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
@Accessors(chain = true)
@TableName(value = "user")
@Builder
public class User {

    @TableId(value = "user_Id",type = IdType.AUTO)
    private Integer userId;

    @TableField(value = "user_Acount")
    private String userAcount;

    @TableField(value = "user_Name")
    private String userName;

    @TableField(value = "user_Phone")
    private String userPhone;

    @TableField(value = "user_Priority")
    private Integer userPriority;

    @TableField(value = "user_Address")
    private String userAddress;

    @TableField(value = "user_Password")
    private String userPassword;

    @TableField(value = "user_Status")
    private Integer userStatus;
}
