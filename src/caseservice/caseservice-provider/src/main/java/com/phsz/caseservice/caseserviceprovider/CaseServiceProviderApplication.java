package com.phsz.caseservice.caseserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CaseServiceProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(CaseServiceProviderApplication.class, args);
	}
}
