package com.phsz.roleplayservice.roleplayserviceprovider.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
public class Responsibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Role role;

    @OneToMany(mappedBy = "responsibility", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"responsibility"})
    @Fetch(FetchMode.SUBSELECT)
    private List<SubResponsibility> subResponsibilities;
}
