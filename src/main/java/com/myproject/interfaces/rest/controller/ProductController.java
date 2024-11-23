package com.myproject.interfaces.rest.controller;

import com.myproject.application.config.MessageResourceConfig;
import com.myproject.common.utility.ResponseUtility;
import com.myproject.domain.ports.inbound.*;
import com.myproject.interfaces.rest.request.AddProductRequest;
import com.myproject.interfaces.rest.request.ProductResponse;
import com.myproject.interfaces.rest.response.AddProductResponse;
import com.myproject.interfaces.rest.response.ResponseTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@AllArgsConstructor
public class ProductController {

    private final MessageResourceConfig messageResourceConfig;
    private final IFetchAllProducts fetchAllProducts;
    private final IFetchProduct fetchProduct;
    private final ICreateProduct createProduct;
    private final IDeleteProduct deleteProduct;
    private final IUpdateProduct updateProduct;
    private final IUpdateOrCreateProduct updateOrCreateProduct;

    @GetMapping()
    public ResponseEntity<ResponseTemplate<List<ProductResponse>>> getProducts() {
        log.info("Received request to fetch all products.");
        List<ProductResponse> productResponses = fetchAllProducts.fetchAllProducts();
        String message = ObjectUtils.isEmpty(productResponses) ?
                messageResourceConfig.getMessage("products.list.not.available") :
                messageResourceConfig.getMessage("products.list.available");
        log.info("Response to the request to fetch all products with {} records.", productResponses.size());
        return new ResponseEntity<>(
                ResponseUtility.generateResponse(productResponses, HttpStatus.OK.value(), message),
                HttpStatus.OK
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseTemplate<ProductResponse>> getProduct(
            @PathVariable("productId") Integer productId) {
        log.info("Request accepted to fetch the product with id: {}.", productId);
        ProductResponse productResponse = fetchProduct.fetchProduct(productId);
        String message = !ObjectUtils.isEmpty(productResponse) ?
                messageResourceConfig.getMessage("product.available") :
                messageResourceConfig.getMessage("product.not.available");
        log.info("Response to the request to fetch product is {}.", productResponse);
        return new ResponseEntity<>(
                ResponseUtility.generateResponse(productResponse, HttpStatus.OK.value(), message),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<ResponseTemplate<AddProductResponse>> addNewProduct(
            @RequestBody AddProductRequest addProductRequest
    ) {
        log.info("Request accepted to add new product with details: {}.", addProductRequest);
        AddProductResponse addProductResponse = createProduct.addNewProduct(addProductRequest);
        String message = !ObjectUtils.isEmpty(addProductResponse) ?
                messageResourceConfig.getMessage("product.created") :
                messageResourceConfig.getMessage("product.not.created");
        log.info("Response to the request to add product is {}.", addProductResponse);
        return new ResponseEntity<>(
                ResponseUtility.generateResponse(addProductResponse, HttpStatus.OK.value(), message),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseTemplate<ProductResponse>> deleteProduct(
            @PathVariable("productId") Integer productId) {
        log.info("Request accepted to delete the product with id: {}.", productId);
        ProductResponse productResponse = deleteProduct.deleteProduct(productId);
        String message = !ObjectUtils.isEmpty(productResponse) ?
                messageResourceConfig.getMessage("product.deleted") :
                messageResourceConfig.getMessage("product.not.deleted");
        log.info("Response to the request to delete product is {}.", productResponse);
        return new ResponseEntity<>(
                ResponseUtility.generateResponse(productResponse, HttpStatus.OK.value(), message),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ResponseTemplate<ProductResponse>> updateProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody AddProductRequest addProductRequest
    ) {
        log.info("Request accepted to update the product with id: {}.", productId);
        ProductResponse productResponse = updateProduct.updateProduct(productId, addProductRequest);
        String message = !ObjectUtils.isEmpty(productResponse) ?
                messageResourceConfig.getMessage("product.updated") :
                messageResourceConfig.getMessage("product.not.updated");
        log.info("Response to the request update the product is {}.", productResponse);
        return new ResponseEntity<>(
                ResponseUtility.generateResponse(productResponse, HttpStatus.OK.value(), message),
                HttpStatus.OK
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResponseTemplate<ProductResponse>> updateOrCreateProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody AddProductRequest addProductRequest
    ) {
        log.info("Request accepted to update the product with id: {}.", productId);
        ProductResponse productResponse = updateOrCreateProduct.updateOrProduct(productId, addProductRequest);
        String message = !ObjectUtils.isEmpty(productResponse) ?
                messageResourceConfig.getMessage("product.updated.or.created") :
                messageResourceConfig.getMessage("product.not.updated.or.created");
        log.info("Response to the request update the product is {}.", productResponse);
        return new ResponseEntity<>(
                ResponseUtility.generateResponse(productResponse, HttpStatus.OK.value(), message),
                HttpStatus.OK
        );
    }
}