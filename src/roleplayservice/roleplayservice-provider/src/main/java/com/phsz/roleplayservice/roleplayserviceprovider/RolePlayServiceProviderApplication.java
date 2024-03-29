package com.phsz.roleplayservice.roleplayserviceprovider;

import com.phsz.roleplayservice.roleplayserviceapi.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
public class RolePlayServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RolePlayServiceProviderApplication.class, args);
    }
}