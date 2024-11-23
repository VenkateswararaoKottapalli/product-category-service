package com.myproject.domain.ports.inbound;

import com.myproject.interfaces.rest.request.AddProductRequest;
import com.myproject.interfaces.rest.request.ProductResponse;

public interface IUpdateProduct {
    ProductResponse updateProduct(Integer productId, AddProductRequest addProductRequest);
}
