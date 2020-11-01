package com.womenstore.hcf.entity.product;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "product")
public class Product {
    @TableId(value = "product_Id", type = IdType.AUTO)
    private Integer productId;

    @TableField(value = "product_Name")
    private String productName;

    @TableField(value = "product_Price")
    private BigDecimal productPrice;

    @TableField(value = "product_Image")
    private String productImage;

    @TableField(value = "product_Detail")
    private String productDetail;

    @TableField(value = "product_Category")
    private Integer productCategory;

    @TableField(value = "product_Inventory")
    private Integer productInventory;

    @TableField(value = "product_Size")
    private String productSize;

    @TableField(value = "product_Status")
    private Integer productStatus;
}