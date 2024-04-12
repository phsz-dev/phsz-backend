package com.phsz.roleplayservice.roleplayserviceprovider.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
public class SubResponsibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnoreProperties(value = {"subResponsibilities"})
    @JoinColumn(name = "responsibility_id", referencedColumnName = "id")
    private Responsibility responsibility;

    // 建立与Procedure的一对多关系
    @OneToMany(mappedBy = "subResponsibility", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"subResponsibility"})
    @Fetch(FetchMode.SUBSELECT)
    private List<Procedure> procedures;

}
