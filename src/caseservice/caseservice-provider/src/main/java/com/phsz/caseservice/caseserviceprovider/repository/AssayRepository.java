package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.Assay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssayRepository extends JpaRepository<Assay, Long> {
    // 这里可以根据需要添加自定义查询方法
    // 例如根据疫苗名称查找：List<Vaccine> findByName(String name);

    Optional<Assay> deleteAssayById(Long id);
}
