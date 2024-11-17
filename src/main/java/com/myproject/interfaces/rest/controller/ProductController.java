package com.myproject.interfaces.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProductController {
    @GetMapping("/api/products")

    public List<String> getProducts() {
        log.info("printing products");
        return List.of("Product 1", "Product 2", "Product 3");
    }
}