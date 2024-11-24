package com.myproject.infrastructure.adaptor;

import com.myproject.domain.ports.outbound.ISaveCategoryPort;
import com.myproject.infrastructure.persistence.CategoryRepository;
import com.myproject.infrastructure.persistence.entity.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class SaveCategoryAdaptor implements ISaveCategoryPort {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
