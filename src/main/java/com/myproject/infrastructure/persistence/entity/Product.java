package com.myproject.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import static com.myproject.infrastructure.persistence.constant.AuditConstant.ID;
import static com.myproject.infrastructure.persistence.constant.ProductConstant.*;

@Getter
@Setter
@Entity
public class Product extends Audit {

    @Column(name = TITLE, nullable = false, unique = true)
    private String title;

    @Column(name = PRICE)
    private Double price;

    @Column(name = CATEGORY_ID, nullable = false)
    private Integer categoryId;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = IMAGE_URL)
    private String image;

    @ManyToOne
    @JoinColumn(name = CATEGORY_ID, referencedColumnName = ID, insertable = false, updatable = false)
    private Category category;
}
