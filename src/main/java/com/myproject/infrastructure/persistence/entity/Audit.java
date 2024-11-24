package com.myproject.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.myproject.infrastructure.persistence.constant.AuditConstant.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Audit {
    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = IS_ACTIVE)
    private Boolean isActive;

    @Column(name = CREATED_DATE)
    private Date createdDate;

    @Column(name = UPDATED_DATE)
    private Date updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
