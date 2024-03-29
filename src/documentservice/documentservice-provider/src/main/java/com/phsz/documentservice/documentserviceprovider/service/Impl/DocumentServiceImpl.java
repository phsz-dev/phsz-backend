package com.phsz.documentservice.documentserviceprovider.service.Impl;

import com.phsz.documentservice.documentserviceprovider.pojo.Document;
import com.phsz.documentservice.documentserviceprovider.repository.DocumentRepository;
import com.phsz.documentservice.documentserviceprovider.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Page<Document> findAllDocuments(Pageable pageable) {
        return documentRepository.findAll(pageable);
    }

    @Override
    public Document findDocumentById(Long id) {
        Optional<Document> byId = documentRepository.findById(id);
        if(byId.isEmpty()){
            return null;
        }
        return byId.get();
    }

    @Override
    public Document addNewDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document updateDocument(Long id, Document documentDetails) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found for this id :: " + id));
        document.setName(documentDetails.getName());

        // 更多属性更新...
        return documentRepository.save(document);
    }

    @Override
    public String deleteDocument(Long id) {
        Optional<Document> byId = documentRepository.findById(id);
        if(byId.isEmpty()){
            return null;
        }
        documentRepository.deleteById(id); // 假设deleteDocumentByDocumentId的功能可以用deleteById替代
        return id.toString(); // 直接返回被删除文档的ID
    }
}