package com.phsz.userservice.userserviceprovider;

import com.phsz.userservice.userserviceapi.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
@ComponentScan({"com.phsz"})
public class UserServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }
}