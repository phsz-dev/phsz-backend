package com.phsz.fileuploadservice.fileuploadserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FileUploadServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileUploadServiceProviderApplication.class, args);
    }
}
