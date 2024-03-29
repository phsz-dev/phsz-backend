package com.phsz.assayservice.assayserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AssayServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssayServiceProviderApplication.class, args);
    }
}