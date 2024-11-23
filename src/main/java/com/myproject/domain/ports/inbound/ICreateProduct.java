package com.myproject.domain.ports.inbound;

import com.myproject.interfaces.rest.request.AddProductRequest;
import com.myproject.interfaces.rest.response.AddProductResponse;

public interface ICreateProduct {
    AddProductResponse addNewProduct(AddProductRequest addProductRequest);
}
