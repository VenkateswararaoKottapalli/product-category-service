package com.myproject.interfaces.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddProductRequest {
    @NotBlank(message = "product.title.size.invalid")
    @Size(max = 255, message = "product.title.size.invalid")
    private String title;
    private Double price;
    @Size(max = 512, message = "product.description.size.invalid")
    private String description;
    private String image;
    @NotNull(message = "category.empty")
    private String category;
}
