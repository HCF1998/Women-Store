package com.womenstore.hcf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.womenstore.hcf.dao")
public class HcfApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcfApplication.class, args);
    }

}
