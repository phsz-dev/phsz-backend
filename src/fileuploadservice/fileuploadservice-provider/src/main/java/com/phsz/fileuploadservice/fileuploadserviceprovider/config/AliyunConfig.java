package com.phsz.fileuploadservice.fileuploadserviceprovider.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application-oss.properties"})
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String urlPrefix;
    private String callbackUrl;
    private String fileHost;
    @Bean
    public OSS oSSClient() {

        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        return ossClient;
    }

    public void closeOSSClient(OSS ossClient) {
        ossClient.shutdown();
    }
}
