package com.phsz.testservice.testserviceprovider.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.phsz.common.JsonbConverter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column(columnDefinition = "text")
    private String text;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = JsonbConverter.class)
    private JsonNode options;
    @Column(columnDefinition = "text")
    private String answer;
}
