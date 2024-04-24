package com.phsz.caseservice.caseserviceprovider.service;

import com.phsz.caseservice.caseserviceprovider.pojo.Assay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AssayService {
    // 支持分页查询的方法
    Page<Assay> findAllAssays(Pageable pageable);

    // 根据ID查找化验
    Assay findAssayById(Long id);

    // 添加新的化验信息
    Assay addNewAssay(Assay assay);

    // 更新化验信息
    Assay updateAssay(Long id, Assay assay);

    // 根据ID删除化验信息
    String deleteAssay(Long id);
}