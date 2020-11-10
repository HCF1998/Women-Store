package com.womenstore.hcf.entity.cart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cart")
public class Cart {
    @TableId(value = "cart_Id", type = IdType.AUTO)
    private Integer cartId;

    @TableField(value = "user_Id")
    private Integer userId;

    @TableField(value = "product_Id")
    private Integer productId;

    @TableField(value = "product_Num")
    private Integer productNum;

    @TableField(value = "product_Price")
    private BigDecimal productPrice;
}