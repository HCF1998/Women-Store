package com.womenstore.hcf.entity.comment;

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
@TableName(value = "`comment`")
public class Comment {
    @TableId(value = "comment_Id", type = IdType.AUTO)
    private Integer commentId;

    @TableField(value = "user_Id")
    private Integer userId;

    @TableField(value = "user_Name")
    private String userName;

    @TableField(value = "product_Id")
    private Integer productId;

    @TableField(value = "comment_Detail")
    private String commentDetail;

    @TableField(value = "bought_Image")
    private String boughtImage;
}