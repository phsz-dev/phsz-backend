package com.phsz.officeservice.officeserviceprovider;

import com.phsz.officeservice.officeserviceapi.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
@EntityScan("com.phsz.officeservice.officeserviceapi.entity")
public class OfficeServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfficeServiceProviderApplication.class, args);
    }
}