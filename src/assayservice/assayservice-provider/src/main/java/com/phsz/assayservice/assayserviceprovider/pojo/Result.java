package com.phsz.assayservice.assayserviceprovider.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Result {
    private int code;       // 状态码
    private String message; // 消息
    private Object data;         // 携带的数据



    // 成功响应，无数据
}
