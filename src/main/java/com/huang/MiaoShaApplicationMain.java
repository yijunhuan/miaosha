package com.huang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MiaoShaApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(MiaoShaApplicationMain.class, args);
    }
}
