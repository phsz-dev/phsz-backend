package com.phsz.fileuploadservice.fileuploadserviceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class OSSFile {
    private MultipartFile file;
    private String host;
}
