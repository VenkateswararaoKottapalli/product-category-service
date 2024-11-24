package com.myproject.interfaces.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCategoryRequest {
    @NotBlank(message = "category.name.size.invalid")
    @Size(max = 255, message = "category.name.size.invalid")
    private String name;
    @Size(max = 512, message = "category.description.size.invalid")
    private String description;
}
