package com.phsz.fileuploadservice.fileuploadserviceprovider.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level level() {
        return Logger.Level.BASIC;
    }
}
