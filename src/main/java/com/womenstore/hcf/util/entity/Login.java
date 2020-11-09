package com.womenstore.hcf.util.entity;

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
@TableName(value = "login")
@Builder
public class Login {

    @TableId(value = "login_Id",type = IdType.AUTO)
    private Integer loginId;

    @TableField(value = "user_Acount")
    private String userAcount;

    @TableField(value = "login_Uuid")
    private String loginUuid;
}
