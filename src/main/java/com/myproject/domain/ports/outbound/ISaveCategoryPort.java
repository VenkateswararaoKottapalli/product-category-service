package com.myproject.domain.ports.outbound;

import com.myproject.infrastructure.persistence.entity.Category;

public interface ISaveCategoryPort {
    Category saveCategory(Category category);
}
