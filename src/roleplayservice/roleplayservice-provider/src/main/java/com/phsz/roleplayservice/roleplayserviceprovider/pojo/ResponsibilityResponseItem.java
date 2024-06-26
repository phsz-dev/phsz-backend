package com.phsz.roleplayservice.roleplayserviceprovider.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibilityResponseItem {
    private Responsibility responsibility;
    private List<Procedure> procedures;

}
