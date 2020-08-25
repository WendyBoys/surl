package com.xuan.surl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.xuan.surl.mapper")
public class SurlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurlApplication.class, args);
    }

}
