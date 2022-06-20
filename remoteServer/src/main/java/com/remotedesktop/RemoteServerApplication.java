package com.remotedesktop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.remotedesktop.mapper")
public class RemoteServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RemoteServerApplication.class,args);
    }
}
