package com.phsz.fileuploadservice.fileuploadserviceapi.client;

import com.phsz.fileuploadservice.fileuploadserviceapi.entity.OSSFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fileuploadservice-provider")
public interface OSSClient {
    @PostMapping("/oss")
    String uploadFile(@RequestBody OSSFile ossFile);
}
