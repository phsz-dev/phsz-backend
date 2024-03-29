package com.phsz.documentservice.documentserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Document", schema = "public")
public class Document {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;
    private String name;
    private String manufacturer; // 注意：此字段在档案上下文中可能不适用，你可能需要根据实际需求进行调整或删除
    private Date expiryDate; // 注意：如果档案没有过期日期，这个字段也可能需要调整或删除

    // 根据档案的实际属性进行相应的修改
}
