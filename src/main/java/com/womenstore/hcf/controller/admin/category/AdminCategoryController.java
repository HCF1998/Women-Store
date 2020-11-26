package com.womenstore.hcf.controller.admin.category;

import com.alibaba.fastjson.JSONObject;
import com.womenstore.hcf.entity.category.Category;
import com.womenstore.hcf.service.impl.CategoryServiceImpl;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 商品分类controller
 */
@Api
@RestController
@RequestMapping(value = "/admin/category")
@Slf4j
public class AdminCategoryController {
  @Autowired private CategoryServiceImpl categoryServiceImpl;

  /**
   * 查询所有商品类别
   * @return 统一返回体
   */
  @ApiOperation("查询所有商品类别")
  @GetMapping("/findAll")
  public Result findAll() {
    List<Category> categoryList = categoryServiceImpl.list();
    return new Result(ResultCode.SUCCESS, categoryList);
  }

  /**
   * 添加商品类别
   * @param jsonObject
   * @return
   */
  @ApiOperation("添加商品类别")
  @PostMapping("/addCategory")
  public Result addCategory(@RequestBody JSONObject jsonObject) {
    log.info("jsonObject:[{}]", jsonObject.toString());
    Category category = new Category();
    category.setCategoryName((String) jsonObject.get("categoryName"));
    categoryServiceImpl.save(category);
    return new Result(ResultCode.SUCCESS, "已成功添加分类：" + category.getCategoryName());
  }

  /**
   * 删除商品类别
   * @param categoryId 商品Id
   * @return
   */
  @ApiOperation("删除商品类别")
  @GetMapping("/deleteCategory/{categoryId}")
  public Result deleteCategory(@PathVariable(value = "categoryId") Integer categoryId) {
    try {
      categoryServiceImpl.removeById(categoryId);
    } catch (Exception e) {
      log.info("Exception:[{}]", e);
      return new Result(ResultCode.FAILED, "删除失败，请前往日志查看具体原因");
    }
    return new Result(ResultCode.SUCCESS, "成功删除分类");
  }

  /**
   * 修改商品类别信息
   * @param jsonObject
   * @return
   */
  @ApiOperation("修改商品类别信息")
  @PostMapping("/editCategory")
  public Result editCategory(@RequestBody JSONObject jsonObject) {
    Integer categoryId = (Integer) jsonObject.get("categoryId");
    Category editCategory = categoryServiceImpl.getById(categoryId);
    String editCategoryName = (String) jsonObject.get("categoryName");
    editCategory.setCategoryName(editCategoryName);
    categoryServiceImpl.updateById(editCategory);
    return new Result(ResultCode.SUCCESS, "成功修改分类名称");
  }
}
