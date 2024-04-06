package com.phsz.testservice.testserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TestServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestServiceProviderApplication.class, args);
    }
}
