package com.phsz.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "phsz.jwt")
public class JwtProperties {

    // getter和setter方法
    private String secret;
    private long expiration;

}
