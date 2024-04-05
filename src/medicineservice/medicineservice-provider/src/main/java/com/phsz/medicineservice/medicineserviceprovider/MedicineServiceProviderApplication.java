package com.phsz.medicineservice.medicineserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class  MedicineServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicineServiceProviderApplication.class, args);
    }
}

