package com.phsz.caseservice.caseserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.phsz.medicineservice.medicineserviceapi.client",
									"com.phsz.vaccineservice.vaccineserviceapi.client",
									"com.phsz.assayservice.assayserviceapi.client"})
public class CaseServiceProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(CaseServiceProviderApplication.class, args);
	}
}
