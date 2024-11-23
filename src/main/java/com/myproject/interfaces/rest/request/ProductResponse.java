package com.myproject.interfaces.rest.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String imageUrl;
}
