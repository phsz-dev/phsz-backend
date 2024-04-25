package com.phsz.testservice.testserviceprovider.pojo;

import java.util.Date;

public interface PaperInfo {
    Long getId();
    String getName();
    String getContent();
    float getTotalScore();
    Long getDurationSeconds();
    Date getDeadline();
}
