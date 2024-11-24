package com.myproject.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.myproject.infrastructure.persistence.constant.CategoryConstant.DESCRIPTION;
import static com.myproject.infrastructure.persistence.constant.CategoryConstant.NAME;

@Getter
@Setter
@Entity
public class Category extends Audit {

    @Column(name = NAME, nullable = false, unique = true)
    private String name;

    @Column(name = DESCRIPTION)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
