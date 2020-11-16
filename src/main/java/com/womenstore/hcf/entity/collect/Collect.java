package com.womenstore.hcf.entity.collect;

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
@TableName(value = "`collect`")
public class Collect {
    @TableId(value = "collect_Id", type = IdType.AUTO)
    private Integer collectId;

    @TableField(value = "user_Id")
    private Integer userId;

    @TableField(value = "product_Id")
    private Integer productId;

    @TableField(value = "collect_Time")
    private Date collectTime;

    @TableField(value = "product_Name")
    private String productName;

    @TableField(value = "product_Price")
    private BigDecimal productPrice;
}