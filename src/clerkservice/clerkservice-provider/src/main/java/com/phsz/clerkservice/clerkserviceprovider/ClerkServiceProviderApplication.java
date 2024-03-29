package com.phsz.clerkservice.clerkserviceprovider;

import com.phsz.clerkservice.clerkserviceapi.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
@EntityScan("com.phsz.clerkservice.clerkserviceapi.entity")
public class ClerkServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClerkServiceProviderApplication.class, args);
    }
}
