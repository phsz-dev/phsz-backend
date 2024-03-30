package com.phsz.medicineservice.medicineserviceapi.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultFeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        // 设置Feign的日志级别为FULL，以便于调试
        return Logger.Level.FULL;
    }
}

