package com.myproject.interfaces.rest.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddProductRequest {
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
