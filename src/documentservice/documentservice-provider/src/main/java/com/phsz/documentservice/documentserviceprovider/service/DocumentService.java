package com.phsz.documentservice.documentserviceprovider.service;

import com.phsz.documentservice.documentserviceprovider.pojo.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentService {
    // 支持分页查询的方法
    Page<Document> findAllDocuments(Pageable pageable);

    // 根据ID查找档案
    Document findDocumentById(Long id);

    // 添加新的档案信息
    Document addNewDocument(Document document);

    // 更新档案信息
    Document updateDocument(Long id, Document document);

    // 根据ID删除档案信息
    String deleteDocument(Long id);
}