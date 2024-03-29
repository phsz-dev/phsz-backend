package com.phsz.vaccineservice.vaccineserviceapi.service;

import com.phsz.vaccineservice.vaccineserviceapi.client.VaccineClient;
import com.phsz.vaccineservice.vaccineserviceapi.pojo.Result;
import com.phsz.vaccineservice.vaccineserviceapi.pojo.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccineService {

    private final VaccineClient vaccineClient;

    @Autowired
    public VaccineService(VaccineClient vaccineClient) {
        this.vaccineClient = vaccineClient;
    }

    // 定义使用VaccineClient的方法来实现业务逻辑
    // 例如获取所有疫苗信息
    public Result getAllVaccines(Integer pageNum, Integer pageSize) {
        return vaccineClient.getAllVaccines(pageNum, pageSize);
    }

    // 其他方法类似...
}
