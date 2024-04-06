package com.phsz.vaccineservice.vaccineserviceprovider.service;

import com.phsz.vaccineservice.vaccineserviceprovider.pojo.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccineService {
    // 支持分页查询的方法
    Page<Vaccine> findAllVaccines(Pageable pageable);

    // 根据ID查找疫苗
    Vaccine findVaccineById(Long id);

    // 添加新的疫苗信息
    Vaccine addNewVaccine(Vaccine vaccine);

    // 更新疫苗信息
    Vaccine updateVaccine(Long id, Vaccine vaccine);

    // 根据ID删除疫苗信息
    String deleteVaccine(Long id);
}