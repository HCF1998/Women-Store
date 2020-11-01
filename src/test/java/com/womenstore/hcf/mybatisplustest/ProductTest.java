package com.womenstore.hcf.mybatisplustest;

import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ProductTest {
  @Autowired private ProductMapper productMapper;

  @Test
  public void addProduct() {
    BigDecimal bigDecimal = new BigDecimal(99.8);
    Product product =
        new Product()
            .builder()
            .productCategory(1)
            .productDetail("棉花")
            .productImage("aa")
            .productInventory(500)
            .productName("花上衣")
            .productPrice(bigDecimal)
            .productSize("XL,X")
            .productStatus(1)
            .build();
    productMapper.insert(product);
  }
}
