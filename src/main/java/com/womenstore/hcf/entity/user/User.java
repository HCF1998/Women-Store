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

    @TableId(value = "userId",type = IdType.AUTO)
    private Integer userId;
    @TableField(value = "userAcount")
    private String userAcount;
    @TableField(value = "userName")
    private String userName;
    @TableField(value = "userPhone")
    private String userPhone;
    @TableField(value = "userPriority")
    private Integer userPriority;
    @TableField(value = "userAddress")
    private String userAddress;
    @TableField(value = "userPassword")
    private String userPassword;
    @TableField(value = "userStatus")
    private Integer userStatus;
}
