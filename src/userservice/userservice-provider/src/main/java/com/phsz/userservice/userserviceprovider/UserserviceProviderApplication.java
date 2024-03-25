package com.phsz.userservice.userserviceprovider;

import com.phsz.userservice.userserviceprovider.pojo.Test;
import com.phsz.userservice.userserviceprovider.repo.testRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class UserserviceProviderApplication {


    private final testRepo testre;

    @Autowired
    public UserserviceProviderApplication(testRepo testre){
        this.testre = testre;
    }


    public static void main(String[] args) {
        SpringApplication.run(UserserviceProviderApplication.class, args);

    }

    @GetMapping("/hello")
    public String testFunc(){
        for(Test test:testre.findAll()){
            return test.toString();
        }
        return "";
    }
}