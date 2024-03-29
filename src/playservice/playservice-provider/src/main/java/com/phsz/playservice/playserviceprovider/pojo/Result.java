package com.phsz.playservice.playserviceprovider.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Result {
	Integer code;
	String message;
	Object data;
}
