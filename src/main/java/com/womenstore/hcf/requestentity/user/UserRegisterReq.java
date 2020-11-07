package com.womenstore.hcf.requestentity.user;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserRegisterReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userRegisterAcount;

    private String userRegisterPassword;

    /**
     * 用户注册密码确认
     */
    private String userRegisterPasswordConfirm;
}
