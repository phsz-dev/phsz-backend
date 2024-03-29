package com.phsz.chargeservice.chargeserviceprovider.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Result {
    private int code;       // 状态码
    private String message; // 消息
    private Object data;         // 携带的数据

}