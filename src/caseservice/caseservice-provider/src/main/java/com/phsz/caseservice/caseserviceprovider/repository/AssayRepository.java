package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.Assay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssayRepository extends JpaRepository<Assay, Long> {
    // 这里可以根据需要添加自定义查询方法
    // 例如根据疫苗名称查找：List<Vaccine> findByName(String name);

    Optional<Assay> deleteAssayById(Long id);

    // 根据关键字key和搜索头（是一个String[]，可能含有多个表头，如果没有就是全部表头）分页查询化验信息
    // 循环searchHeader，用sql循环

    Page<Assay> searchAssay(String key, String[] searchHeader, Pageable pageable);

    // 对Assay中所有column中信息都要查找
    @Query(value = "SELECT * FROM assay WHERE id LIKE :key OR name LIKE :key OR type LIKE :key OR price LIKE :key OR description LIKE :key", nativeQuery = true)
    Page<Assay> searchAllAssay(String key, Pageable pageable);
}
