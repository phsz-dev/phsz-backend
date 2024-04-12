package com.phsz.roleplayservice.roleplayserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RolePlayServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RolePlayServiceProviderApplication.class, args);
    }
}
