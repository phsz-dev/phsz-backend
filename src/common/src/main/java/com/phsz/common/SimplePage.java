package com.phsz.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class SimplePage<E> {

    private List<E> content;
    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private String orderColumn;
    private String orderType;

    public SimplePage(Page<E> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        page.getSort().get().forEach(order -> {
            this.orderColumn = order.getProperty();
            this.orderType = order.getDirection().name();
        });
    }

}
