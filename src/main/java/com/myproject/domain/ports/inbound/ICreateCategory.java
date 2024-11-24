package com.myproject.domain.ports.inbound;

import com.myproject.interfaces.rest.request.CreateCategoryRequest;
import com.myproject.interfaces.rest.response.CreateCategoryResponse;

public interface ICreateCategory {
    CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
}
