package com.phsz.hospitalizationservice.hospitalizationserviceprovider;

import com.phsz.hospitalizaionservice.hospitalizaionserviceapi.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
public class HospitalizationServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalizationServiceProviderApplication.class, args);
    }
}
