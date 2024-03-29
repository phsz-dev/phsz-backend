package com.phsz.assayservice.assayserviceprovider.pojo;

import java.util.Date;

public class Assay {
    private Long id;
    private String name;
    private String manufacturer;
    private Date expiryDate;

    public Assay() {
    }

    public Assay(Long id, String name, String manufacturer, Date expiryDate) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}