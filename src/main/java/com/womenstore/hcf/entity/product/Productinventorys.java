package com.womenstore.hcf.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "productinventorys")
public class Productinventorys {
    @TableId(value = "productInventorys_Id", type = IdType.AUTO)
    private Integer productinventorysId;

    @TableField(value = "product_Id")
    private Integer productId;

    @TableField(value = "product_Name")
    private String productName;

    @TableField(value = "product_Size")
    private String productSize;

    @TableField(value = "product_Inventory")
    private Integer productInventory;
}