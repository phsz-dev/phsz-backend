package com.phsz.common.OSS;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class AliyunOSS {
	private String endpoint;
	private EnvironmentVariableCredentialsProvider credentialsProvider;
	private String bucketName;


	public AliyunOSS() throws Exception{
		// Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
		String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
		// 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
		EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
		// 填写Bucket名称，例如examplebucket。
		String bucketName = "examplebucket";
	}

	public String upload(MultipartFile file) throws IOException {
		OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
		try {
			InputStream inputStream = file.getInputStream();
			ossClient.putObject(bucketName, file.getOriginalFilename(), inputStream);
		} catch (OSSException oe) {
			System.out.println("Caught an OSSException, which means your request made it to OSS, "
					+ "but was rejected with an error response for some reason.");
			System.out.println("Error Message:" + oe.getErrorMessage());
			System.out.println("Error Code:" + oe.getErrorCode());
			System.out.println("Request ID:" + oe.getRequestId());
			System.out.println("Host ID:" + oe.getHostId());
		} catch (ClientException ce) {
			System.out.println("Caught an ClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with OSS, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message:" + ce.getMessage());
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
		return "https://" + bucketName + "." + endpoint + "/" + file.getOriginalFilename();
	}
}
