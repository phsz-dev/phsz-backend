package com.phsz.documentservice.documentserviceprovider.controller;

import com.phsz.documentservice.documentserviceprovider.pojo.Document;
import com.phsz.documentservice.documentserviceprovider.pojo.Result;
import com.phsz.documentservice.documentserviceprovider.service.Impl.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    Result result;
    @Autowired
    private DocumentServiceImpl documentService;

    // 获取所有档案
    @GetMapping
    public Result getAllDocuments(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        result.setData(documentService.findAllDocuments(pageable));
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 添加新档案
    @PostMapping
    public Result addNewDocument(@RequestBody Document document) {
        Document newDocument = documentService.addNewDocument(document);
        if (newDocument == null) {
            result.setCode(0);
            result.setMessage("already exists");
            return result;
        }
        result.setData(newDocument);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 删除档案
    @DeleteMapping("/{document_id}")
    public Result deleteDocumentById(@PathVariable Long document_id) {
        String message = documentService.deleteDocument(document_id);
        if (message == null) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(message);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 修改档案信息
    @PutMapping("/{document_id}")
    public Result updateDocumentById(@PathVariable Long document_id, @RequestBody Document document) {
        Document updatedDocument = documentService.updateDocument(document_id, document);
        if (updatedDocument == null) {
            result.setCode(0);
            result.setMessage("not found or error updating");
            return result;
        }
        result.setData(updatedDocument);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 获取单个档案信息
    @GetMapping("/{document_id}")
    public Result findDocumentById(@PathVariable Long document_id) {
        Document document = documentService.findDocumentById(document_id);
        if (document == null) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(document);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }
}