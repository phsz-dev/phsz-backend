package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoughCaseInfo {
    private Long id;
    private String name;
    private String description;
    private String submitTime;
}
