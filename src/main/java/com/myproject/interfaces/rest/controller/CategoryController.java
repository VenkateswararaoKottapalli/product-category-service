package com.myproject.interfaces.rest.controller;

import com.myproject.application.config.MessageResourceConfig;
import com.myproject.common.utility.ResponseUtility;
import com.myproject.domain.ports.inbound.ICreateCategory;
import com.myproject.interfaces.rest.request.CreateCategoryRequest;
import com.myproject.interfaces.rest.response.CreateCategoryResponse;
import com.myproject.interfaces.rest.response.ResponseTemplate;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    private final MessageResourceConfig messageResourceConfig;
    private final ICreateCategory createCategory;

    @PostMapping()
    public ResponseEntity<ResponseTemplate<CreateCategoryResponse>> createCategory(
            @RequestBody @Valid CreateCategoryRequest createCategoryRequest
    ) {
        log.info("Request accepted to create new category with details: {}.", createCategoryRequest);
        CreateCategoryResponse createCategoryResponse = createCategory.createCategory(createCategoryRequest);
        String message = !ObjectUtils.isEmpty(createCategoryResponse) ?
                messageResourceConfig.getMessage("category.created") :
                messageResourceConfig.getMessage("category.not.created");
        log.info("Response to the request to create new category is {}.", createCategoryResponse);
        return new ResponseEntity<>(
                ResponseUtility.generateResponse(createCategoryResponse, HttpStatus.OK.value(), message),
                HttpStatus.OK
        );
    }
}
