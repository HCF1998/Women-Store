package com.womenstore.hcf.entity.orders;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "orders")
public class Orders {
    @TableId(value = "order_Id", type = IdType.AUTO)
    private Integer orderId;

    @TableField(value = "order_No")
    private String orderNo;

    @TableField(value = "order_CreateTime")
    private Date orderCreatetime;

    @TableField(value = "order_Status")
    private Integer orderStatus;

    @TableField(value = "order_PayTime")
    private Date orderPaytime;

    @TableField(value = "order_Sum")
    private BigDecimal orderSum;

    @TableField(value = "order_ProductNum")
    private Integer orderProductnum;

    @TableField(value = "user_Id")
    private Integer userId;

    @TableField(value = "user_Phone")
    private String userPhone;

    @TableField(value = "user_Address")
    private String userAddress;

    @TableField(value = "product_Id")
    private Integer productId;

    @TableField(value = "product_Name")
    private String productName;

    @TableField(value = "product_Price")
    private BigDecimal productPrice;
}