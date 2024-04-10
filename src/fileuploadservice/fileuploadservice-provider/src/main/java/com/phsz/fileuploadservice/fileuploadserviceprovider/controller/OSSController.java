package com.phsz.fileuploadservice.fileuploadserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.fileuploadservice.fileuploadserviceapi.entity.OSSFile;
import com.phsz.fileuploadservice.fileuploadserviceprovider.service.FileUploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    public FileUploadServiceImpl fileUploadService;

//    @PostMapping
//    public String uploadFile(@RequestBody OSSFile ossFile){
//        return fileUploadService.uploadFile(ossFile.getFile(),ossFile.getHost());
//    }

    @PostMapping("/avatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile file, @RequestHeader("UserId") String userId){
        return Result.success("上传成功",fileUploadService.uploadAvatar(file,"profile",userId));
    }

}
