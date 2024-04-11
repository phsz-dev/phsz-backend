package com.phsz.testservice.testserviceprovider.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.JsonNode;
import com.phsz.common.JsonbConverter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @JsonIgnore
    private String answer;
}
