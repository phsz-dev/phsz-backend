package com.phsz.playservice.playserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PlayServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayServiceProviderApplication.class, args);
    }
}
