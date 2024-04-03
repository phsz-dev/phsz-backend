package com.phsz.playservice.playserviceprovider.pojo;

import lombok.Data;

@Data
public class Procedure {
	private Long id;
	private String name;
	private Long responsibilityId;
	private String content;
	private Integer rank;
}
