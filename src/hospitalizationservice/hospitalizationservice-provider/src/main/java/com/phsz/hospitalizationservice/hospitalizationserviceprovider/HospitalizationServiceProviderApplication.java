package com.phsz.hospitalizationservice.hospitalizationserviceprovider;

import com.phsz.hospitalizationservice.hospitalizationserviceapi.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
@EntityScan("com.phsz.hospitalizationservice.hospitalizationserviceapi.entity")
public class HospitalizationServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalizationServiceProviderApplication.class, args);
    }
}
