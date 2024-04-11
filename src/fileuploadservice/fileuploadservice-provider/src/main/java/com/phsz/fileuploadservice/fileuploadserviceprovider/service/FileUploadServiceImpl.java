package com.phsz.fileuploadservice.fileuploadserviceprovider.service;

import com.aliyun.oss.OSS;
import com.phsz.fileuploadservice.fileuploadserviceapi.client.OSSClient;
import com.phsz.fileuploadservice.fileuploadserviceprovider.config.AliyunConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;





    public String uploadFile(MultipartFile file,String fileHost){
        String bucketNanme=aliyunConfig.getBucketName();
        String endPoint = aliyunConfig.getEndpoint();
        String accessKeyId = aliyunConfig.getAccessKeyId();
        String accessKeySecret = aliyunConfig.getAccessKeySecret();
        String returnUrl="";
        String originalFilename = file.getOriginalFilename();
        //截取文件类型
        String fileType;
        if(originalFilename.lastIndexOf(".")==-1){
            fileType = "unknown";
        }else{
            fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID().toString().replace("-", "")+fileType;
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String uploadUrl = fileHost+"/"+filePath+"/"+newFileName;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketNanme,uploadUrl,inputStream);
        returnUrl = aliyunConfig.getUrlPrefix()+"/"+uploadUrl;
        return returnUrl;


    }


    public String uploadAvatar(MultipartFile file, String host, String userId){
        String bucketNanme=aliyunConfig.getBucketName();
        String endPoint = aliyunConfig.getEndpoint();
        String accessKeyId = aliyunConfig.getAccessKeyId();
        String accessKeySecret = aliyunConfig.getAccessKeySecret();
        String returnUrl="";
        String originalFilename = file.getOriginalFilename();
        //截取文件类型
        String fileType;
        if(originalFilename.lastIndexOf(".")==-1){
            fileType = "unknown";
        }else{
            fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID()+userId+fileType;
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String uploadUrl = host+"/"+filePath+"/"+newFileName;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketNanme,uploadUrl,inputStream);
        returnUrl = aliyunConfig.getUrlPrefix()+"/"+uploadUrl;
        return returnUrl;
    }
}
