package com.womenstore.hcf.requestentity.user;

import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserLoginReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userAcount;

    private String userPassword;
}
