package com.phsz.documentservice.documentserviceprovider.repository;

import com.phsz.documentservice.documentserviceprovider.pojo.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // 这里可以根据需要添加自定义查询方法
    // 例如根据档案名称查找：List<Document> findByName(String name);
    Optional<Document> deleteDocumentByDocumentId(Long documentId);
}