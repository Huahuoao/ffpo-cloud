package com.huahuo.stamp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @作者 花火
 * @创建日期 2022/10/25 11:28
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.huahuo.stamp.mapper")
public class StampApplication {
    public static void main(String[] args) {
        SpringApplication.run(StampApplication.class,args);
    }
}
