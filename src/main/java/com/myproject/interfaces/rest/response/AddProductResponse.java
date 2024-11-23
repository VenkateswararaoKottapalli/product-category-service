package com.myproject.interfaces.rest.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddProductResponse {
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
