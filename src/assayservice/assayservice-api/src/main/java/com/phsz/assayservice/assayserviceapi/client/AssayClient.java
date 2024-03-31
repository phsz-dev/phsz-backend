package com.phsz.assayservice.assayserviceapi.client;

import com.phsz.assayservice.assayserviceapi.pojo.Assay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "assayservice-provider")
public interface AssayClient {

	@GetMapping("/api/assays/client/{assayId}")
	public Assay getAssayById(@PathVariable("assayId") Long assayId);

}
