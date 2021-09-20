package com.code.springbootalipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.code"})
@SpringBootApplication
public class SpringbootAlipayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAlipayApplication.class, args);
    }
}
